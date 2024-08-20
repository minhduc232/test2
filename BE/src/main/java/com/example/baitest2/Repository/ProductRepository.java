package com.example.baitest2.Repository;

import com.example.baitest2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Catalog c inner join Product p on c.id = p.catalog.id where c.id = :catalog_id")
    List<Product> findByCatalog(@Param("catalog_id") Long catalogId);

    List<Product> findByNameLike(String name);

}