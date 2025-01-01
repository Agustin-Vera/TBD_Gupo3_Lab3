package com.example.TBDBackend.initializers;

import com.example.TBDBackend.models.Category;
import com.example.TBDBackend.models.Product;
import com.example.TBDBackend.repositories.CategoryRepository;
import com.example.TBDBackend.repositories.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Component
public class ProductInitializer implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductInitializer(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args){
        if (productRepository.count() == 0) {
            Optional<Category> electronicsCategory = categoryRepository.findByName("Electronica"); 
            Optional<Category> booksCategory = categoryRepository.findByName("Libro");

            if (electronicsCategory.isPresent() && booksCategory.isPresent()) {
                Product product1 = Product.builder()
                        .name("Celular")
                        .description("último modelo de Samsung")
                        .price(new BigDecimal("700000"))
                        .stock(50)
                        .state("Disponible")
                        .category(electronicsCategory.get())
                        .build();

                Product product2 = Product.builder()
                        .name("Laptop")
                        .description("Laptop para videojuegos")
                        .price(new BigDecimal("1000000"))
                        .stock(30)
                        .state("Disponible")
                        .category(electronicsCategory.get())
                        .build();

                Product product3 = Product.builder()
                        .name("Audífonos")
                        .description("Audífonos con cancelación de ruido")
                        .price(new BigDecimal("130000"))
                        .stock(100)
                        .state("Disponible")
                        .category(electronicsCategory.get())
                        .build();

                Product product4 = Product.builder()
                        .name("Libro de cocina")
                        .description("Recetas especiales para año nuevo")
                        .price(new BigDecimal("30000"))
                        .stock(200)
                        .state("Disponible")
                        .category(booksCategory.get())
                        .build();

                Product product5 = Product.builder()
                        .name("Los Juegos del Hambre")
                        .description("Primer libro de la trilogía de Los Juegos del Hambre")
                        .price(new BigDecimal("20000"))
                        .stock(150)
                        .state("Disponible")
                        .category(booksCategory.get())
                        .build();

                // Guarda los productos en la base de datos
                productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
                System.out.println("Productos iniciales cargados.");
            } else {
                System.out.println("No se encontraron las categorías necesarias.");
            }
        } else {
            System.out.println("Los productos ya están presentes en la base de datos.");
        }
    }
}


