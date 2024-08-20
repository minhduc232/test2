package com.example.baitest2.service;


import com.example.baitest2.dto.CatalogResponse;

import java.util.List;

public interface CatalogService {
    List<CatalogResponse> getAllCatalogs();
}
