package com.monggovest.MonggoVestBackEnd.pagination;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
    @Autowired
    private PaginationDao paginationDao;
    public Page<ProductModel> findJsonDataByCondition(Long provinceId, String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("ASC")) {
            sort = new Sort(new Sort.Order(Sort.Direction.ASC, orderBy));
        }
        if (direction.equals("DESC")) {
            sort = new Sort(new Sort.Order(Sort.Direction.DESC, orderBy));
        }
        Pageable pageable = new PageRequest(page, size, sort);
        Page<ProductModel> data = paginationDao.findAllByProvinceId(provinceId, pageable);
        return data;
    }
}