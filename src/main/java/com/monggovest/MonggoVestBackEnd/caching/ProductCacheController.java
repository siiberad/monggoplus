package com.monggovest.MonggoVestBackEnd.caching;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductCacheController {
    @Autowired
    private IProductCacheService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Long id) {
        ProductModel article = articleService.getProductById(id);
        return new ResponseEntity<ProductModel>(article, HttpStatus.OK);
    }
    @GetMapping("caches")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> list = articleService.getAllProducts();
        return new ResponseEntity<List<ProductModel>>(list, HttpStatus.OK);
    }
    @PostMapping("cache")
    public ResponseEntity<Void> addProduct(@RequestBody ProductModel product, UriComponentsBuilder builder) {
        ProductModel savedArticle = articleService.addProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(savedArticle.getProductId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("cache")
    public ResponseEntity<ProductModel> updateProduct(@RequestBody ProductModel product) {
        articleService.updateProduct(product);
        return new ResponseEntity<ProductModel>(product, HttpStatus.OK);
    }
    @DeleteMapping("cache/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
//    @PutMapping(path = "/{userId}")
//    public UserModel update(@PathVariable("userId") Long userId, @RequestBody UserModel user) throws BadHttpRequest {
//        if (repository.existsById(userId)) {
//            user.setUserId(userId);
//            return repository.save(user);
//        } else {
//            throw new BadHttpRequest();
//        }
//    }
}