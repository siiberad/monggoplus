//package com.monggovest.MonggoVestBackEnd.caching;
//
//import com.monggovest.MonggoVestBackEnd.model.ProductModel;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//
//public class RestClientUtil {
//    public void getArticleByIdDemo(long id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/user/article/{id}";
//        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
//        ResponseEntity<ProductModel> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ProductModel.class, id);
//        ProductModel article = responseEntity.getBody();
//        System.out.println("Id:"+article.getProductId()+", Name:"+article.getProductName()
//                +", Price:"+article.getProductPrice()+", Image:"+article.getProductImage1()+", Contract:"+article.getLabaInvestor());
//    }
//    public void getAllArticlesDemo() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/user/articles";
//        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
//        ResponseEntity<ProductModel[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ProductModel[].class);
//        ProductModel[] articles = responseEntity.getBody();
//        for(ProductModel article : articles) {
//            System.out.println("Id:"+article.getProductId()+", Name:"+article.getProductName()
//                    +", Price:"+article.getProductPrice()+", Image:"+article.getProductImage1()+", Contract:"+article.getLabaInvestor());
//        }
//    }
//    public void addArticleDemo(ProductModel objArticle) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/user/article";
//        HttpEntity<ProductModel> requestEntity = new HttpEntity<ProductModel>(objArticle, headers);
//        URI uri = restTemplate.postForLocation(url, requestEntity);
//        System.out.println(uri.getPath());
//    }
//    public void updateArticleDemo(ProductModel objArticle) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/user/article";
//        HttpEntity<ProductModel> requestEntity = new HttpEntity<ProductModel>(objArticle, headers);
//        restTemplate.put(url, requestEntity);
//    }
//    public void deleteArticleDemo(long id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/user/article/{id}";
//        HttpEntity<ProductModel> requestEntity = new HttpEntity<ProductModel>(headers);
//        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, id);
//    }
////    public static void main(String args[]) {
////        RestClientUtil util = new RestClientUtil();
////        //Add article
////        ProductModel objArticle = new Article();
////        objArticle.setTitle("Spring REST Security");
////        objArticle.setCategory("Spring");
////        //util.addArticleDemo(objArticle);
////
////        //Update article
////        objArticle.setArticleId(1);
////        objArticle.setTitle("Java Concurrency");
////        objArticle.setCategory("Java");
////        //util.updateArticleDemo(objArticle);
////
////        //util.deleteArticleDemo(2);
////        util.getArticleByIdDemo(1);
////        System.out.println("---- All articles ----");
////        util.getAllArticlesDemo();
////    }
//}
