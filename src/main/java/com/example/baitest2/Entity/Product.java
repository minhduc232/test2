package com.example.baitest2.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Data
@Setter
@Getter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "name",length = 150)
    private String name;

    @Column(name = "picture",length = 200)
    private String picture;

    @Column(name = "Price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "catalog_Id")
    private Catalog catalog;

}
