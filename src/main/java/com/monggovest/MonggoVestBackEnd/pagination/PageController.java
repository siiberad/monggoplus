package com.monggovest.MonggoVestBackEnd.pagination;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/pagination")
public class PageController {

    @Autowired
    private PaginationService paginationService;
    @RequestMapping(value = "/conditionalPagination", params = { "groupedBy","orderBy", "direction", "page",
            "size" }, method = RequestMethod.GET)
    @ResponseBody
    public Page<ProductModel> findJsonDataByPageAndSize(@RequestParam("groupedBy") Long provinceId,@RequestParam("orderBy") String orderBy,
                                                        @RequestParam("direction") String direction, @RequestParam("page") int page,
                                                        @RequestParam("size") int size) {
        if (!(direction.equals(Direction.ASCENDING.getDirectionCode())
                || direction.equals(Direction.DESCENDING.getDirectionCode()))) {
            throw new PaginationSortingException("Invalid sort direction");
        }

        // ini buat sorting

        if (!(orderBy.equals(OrderBy.HARGAMODAL.getOrderByCode()) || orderBy.equals(OrderBy.PRODUCTID.getOrderByCode()))) {
            throw new PaginationSortingException("Invalid orderBy condition");
        }
        Page<ProductModel> list = paginationService.findJsonDataByCondition(provinceId, orderBy, direction, page, size);
        return list;
    }
    @ExceptionHandler(PaginationSortingException.class)
    public ResponseEntity<PagingSortingErrorResponse> exceptionHandler(Exception ex) {
        PagingSortingErrorResponse pagingSortingErrorResponse = new PagingSortingErrorResponse();
        pagingSortingErrorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        pagingSortingErrorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<PagingSortingErrorResponse>(pagingSortingErrorResponse, HttpStatus.OK);
    }
}
