package com.example.baitest2.service.impl;


import com.example.baitest2.Repository.CatalogRepository;
import com.example.baitest2.dto.CatalogResponse;
import com.example.baitest2.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final ModelMapper modelMapper;
    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogResponse> getAllCatalogs() {
        return catalogRepository.findAll().stream()
                .map(catalog -> modelMapper.map(catalog, CatalogResponse.class))
                .collect(Collectors.toList());
    }

}
