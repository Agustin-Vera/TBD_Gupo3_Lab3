package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.CategoryDTO;
import com.example.TBDBackend.models.Category;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(String id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        return category.get();
    }

    public Category saveCategory(CategoryDTO categoryDTO) {
        Optional<Category> possibleCategory = categoryRepository.findByName(categoryDTO.getName());

        if (possibleCategory.isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();

        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }

        Category updatedCategory = Category.builder()
                .id(category.get().getId())
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }


}
