package com.monggovest.MonggoVestBackEnd.caching;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductCacheRepository extends CrudRepository<ProductModel, Long> {
}
