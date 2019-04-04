package com.monggovest.MonggoVestBackEnd.pagination;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import com.monggovest.MonggoVestBackEnd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaginationServiceSearch {
    @Autowired
    private ProductRepository productRepository;
    public Page<ProductModel> searchname (String name, String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("ASC")) {
            sort = new Sort(new Sort.Order(Sort.Direction.ASC, orderBy));
        }
        if (direction.equals("DESC")) {
            sort = new Sort(new Sort.Order(Sort.Direction.DESC, orderBy));
        }
        Pageable pageable = new PageRequest(page, size, sort);
        Page<ProductModel> data = productRepository.findByProductNameContaining(name,pageable);
        return data;
    }
}
