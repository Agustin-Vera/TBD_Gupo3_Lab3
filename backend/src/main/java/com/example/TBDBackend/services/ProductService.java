package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.ProductDTO;
import com.example.TBDBackend.entities.ProductEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity findProductById(String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (productEntity.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }

        return productEntity.get();
    }

    public ProductEntity saveProduct(ProductDTO productDTO) {
        ProductEntity product = ProductEntity.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .state(productDTO.getState())
                .build();

        return productRepository.save(product);
    }

    public ProductEntity updateProduct(String id, ProductDTO productDTO) {
        Optional<ProductEntity> possibleProduct = productRepository.findById(id);

        if (possibleProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }

        ProductEntity updatedProduct = ProductEntity.builder()
                .id(possibleProduct.get().getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .state(productDTO.getState())
                .build();

        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }

        productRepository.deleteById(id);
    }
}
