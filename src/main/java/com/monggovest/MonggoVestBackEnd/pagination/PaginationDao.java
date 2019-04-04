package com.monggovest.MonggoVestBackEnd.pagination;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaginationDao extends CrudRepository<ProductModel, Long> {
    @Query("select u from ProductModel u where u.provinceModel.provinceId = ?1")
    Page<ProductModel> findAllByProvinceId (Long provinceId, Pageable pageable);
}
