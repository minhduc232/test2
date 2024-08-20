package com.example.baitest2.Controller;


import com.example.baitest2.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("")
    public ResponseEntity<?> getAllCatalogs() {
        return ResponseEntity.ok(catalogService.getAllCatalogs());
    }



}
