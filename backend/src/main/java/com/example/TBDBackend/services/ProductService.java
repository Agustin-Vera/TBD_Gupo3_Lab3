package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.ProductDTO;
import com.example.TBDBackend.entities.CategoryEntity;
import com.example.TBDBackend.entities.ClientEntity;
import com.example.TBDBackend.entities.LogEntity;
import com.example.TBDBackend.entities.ProductEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.jwt.JwtUtil;
import com.example.TBDBackend.repositories.CategoryRepository;
import com.example.TBDBackend.repositories.ClientRepository;
import com.example.TBDBackend.repositories.LogRepository;
import com.example.TBDBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AuthService authService;

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

        if (possibleProduct.get().getPrice() != productDTO.getPrice()){
            ClientEntity client = authService.getAuthClient();

            Date newDate = new Date();

            LogEntity newLog = LogEntity.builder()
                    .originalPrice(possibleProduct.get().getPrice())
                    .newPrice(productDTO.getPrice())
                    .client(client)
                    .updateDate(newDate)
                    .build();

            logRepository.save(newLog);
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
