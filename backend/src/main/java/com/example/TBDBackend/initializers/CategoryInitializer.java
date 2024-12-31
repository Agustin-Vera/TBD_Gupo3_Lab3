package com.example.TBDBackend.initializers;

import com.example.TBDBackend.models.Category;
import com.example.TBDBackend.repositories.CategoryRepository; // Asegúrate de tener un repositorio para Category
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryInitializer implements ApplicationRunner {

    private CategoryRepository categoryRepository;

    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Verifica si ya hay categorías en la base de datos
        if (categoryRepository.count() == 0) {
            // Crea algunas categorías iniciales
            Category category1 = Category.builder()
                    .name("Electronica")
                    .build();

            Category category2 = Category.builder()
                    .name("Libro")
                    .build();

            // Guarda las categorías en la base de datos
            categoryRepository.saveAll(Arrays.asList(category1, category2));
            System.out.println("Categorías iniciales cargadas.");
        } else {
            System.out.println("Las categorías ya están presentes en la base de datos.");
        }
    }
}

