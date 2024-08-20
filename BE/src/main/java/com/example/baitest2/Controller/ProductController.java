package com.example.baitest2.Controller;


import com.example.baitest2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/catalogs/{catalog-id}/products")
    public ResponseEntity<?> getProductsByCatalog(@PathVariable(name = "catalog-id") Long catalogId) {
        var responses = productService.findProductsByCatalog(catalogId);
        return (responses.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductsByName(@RequestParam(name = "product-name", required = false) String productName) {
        if (StringUtils.hasText(productName)) {
            var responses = productService.getAllProducts();
            return (responses.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
        }
        var responses = productService.findProductsByName(productName);
        return (responses.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
    }

}
