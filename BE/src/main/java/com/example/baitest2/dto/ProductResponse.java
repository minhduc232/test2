package com.example.baitest2.dto;


import com.example.baitest2.entity.Catalog;
import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String picture;

    private Long price;

    private Catalog catalog;

}
