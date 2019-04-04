package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
 Page<ProductModel> findByProductNameContaining(String name, Pageable pageable);

// @Query(value = "SELECT * FROM products WHERE province_id = ?1",
////         countQuery = "SELECT count(*) FROM product WHERE province_id = ?1",
//         nativeQuery = true)
// Page<ProductModel> findAllByProvinceId(Long provinceId, Pageable pageable);
//    @Query(value="SELECT * FROM products WHERE province_id = ?1",  nativeQuery = true)
//    List<ProductModel> findAllProvince (Long provinceId);
}
