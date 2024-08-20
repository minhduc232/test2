package com.example.baitest2.service;

import com.example.baitest2.dto.ProductResponse;


import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    List<ProductResponse> findProductsByCatalog(Long catalogId);

    List<ProductResponse> findProductsByName(String productName);
}
