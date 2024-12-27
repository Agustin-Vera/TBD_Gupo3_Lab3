package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.ProductDTO;
import com.example.TBDBackend.entities.ProductEntity;
import com.example.TBDBackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable String id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> postProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
