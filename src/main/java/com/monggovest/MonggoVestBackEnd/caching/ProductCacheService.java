package com.monggovest.MonggoVestBackEnd.caching;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCacheService implements IProductCacheService {
    @Autowired
    private ProductCacheRepository productCacheRepository;

    @Override
    @Cacheable(value= "productCache", key= "#productId")
    public ProductModel getProductById(long productId) {
        System.out.println("--- Inside getProductById() ---");
        return productCacheRepository.findById(productId).get();
    }
    @Override
    @Cacheable(value= "allProductsCache", unless= "#result.size() == 0")
    public List<ProductModel> getAllProducts(){
        System.out.println("--- Inside getAllProducts() ---");
        List<ProductModel> list = new ArrayList<>();
        productCacheRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
    @Override
    @Caching(
            put= { @CachePut(value= "productCache", key= "#product.productId") },
            evict= { @CacheEvict(value= "allProductsCache", allEntries= true) }
    )
    public ProductModel addProduct(ProductModel product){
        System.out.println("--- Inside addProduct() ---");
        return productCacheRepository.save(product);
    }
    @Override
    @Caching(
            put= { @CachePut(value= "productCache", key= "#product.productId") },
            evict= { @CacheEvict(value= "allProductsCache", allEntries= true) }
    )
    public ProductModel updateProduct(ProductModel product) {
        System.out.println("--- Inside updateProduct() ---");
        return productCacheRepository.save(product);
    }
    @Override
    @Caching(
            evict= {
                    @CacheEvict(value= "productCache", key= "#productId"),
                    @CacheEvict(value= "allProductsCache", allEntries= true)
            }
    )
    public void deleteProduct(long productId) {
        System.out.println("--- Inside deleteArticle() ---");
        productCacheRepository.delete(productCacheRepository.findById(productId).get());
    }
}
