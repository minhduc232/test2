package com.example.baitest2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "catalog", orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<>();

}