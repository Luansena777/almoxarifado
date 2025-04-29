package com.almoxarifado.controller;

import com.almoxarifado.dto.ProductDTO;
import com.almoxarifado.exception.ProductNotFoundException;
import com.almoxarifado.model.Product;
import com.almoxarifado.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> showProducts() {
        return ResponseEntity.ok(productService.showProducts());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> findByCode(@PathVariable String code) {
            Product product = productService.findByCode(code);
            return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("/{code}")
    public ResponseEntity<Void> updateProduct(@PathVariable String code, @Valid @RequestBody ProductDTO updatedProduct) {
        productService.updateProduct(code, updatedProduct);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String code) {
        productService.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }

}
