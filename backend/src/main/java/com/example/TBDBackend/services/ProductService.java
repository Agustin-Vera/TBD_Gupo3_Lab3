package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.ProductDTO;
import com.example.TBDBackend.entities.CategoryEntity;
import com.example.TBDBackend.entities.ProductEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.CategoryRepository;
import com.example.TBDBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        Optional<CategoryEntity> possibleCategory = categoryRepository.findById(productDTO.getCategoryId());

        if (possibleCategory.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }

        ProductEntity product = ProductEntity.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .state(productDTO.getState())
                .category(possibleCategory.get())
                .build();

        return productRepository.save(product);
    }

    public ProductEntity updateProduct(String id, ProductDTO productDTO) {
        Optional<ProductEntity> possibleProduct = productRepository.findById(id);

        if (possibleProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }

        Optional<CategoryEntity> possibleCategory = categoryRepository.findById(productDTO.getCategoryId());

        if (possibleCategory.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }

        ProductEntity updatedProduct = ProductEntity.builder()
                .id(possibleProduct.get().getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .state(productDTO.getState())
                .category(possibleCategory.get())
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
