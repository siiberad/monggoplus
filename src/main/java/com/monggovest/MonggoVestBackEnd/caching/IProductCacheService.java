package com.monggovest.MonggoVestBackEnd.caching;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;

import java.util.List;

public interface IProductCacheService {
    List<ProductModel> getAllProducts();
    ProductModel getProductById(long productId);
    ProductModel addProduct(ProductModel product);
    ProductModel updateProduct(ProductModel product);
    void deleteProduct(long productId);
}
