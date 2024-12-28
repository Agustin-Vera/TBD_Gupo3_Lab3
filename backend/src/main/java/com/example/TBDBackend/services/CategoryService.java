package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.CategoryDTO;
import com.example.TBDBackend.entities.CategoryEntity;
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

    public List<CategoryEntity> findAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity findCategoryById(String id) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        return category.get();
    }

    public CategoryEntity saveCategory(CategoryDTO categoryDTO) {
        Optional<CategoryEntity> possibleCategory = categoryRepository.findByName(categoryDTO.getName());

        if (possibleCategory.isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        CategoryEntity category = CategoryEntity.builder()
                .name(categoryDTO.getName())
                .build();

        return categoryRepository.save(category);
    }

    public CategoryEntity updateCategory(String id, CategoryDTO categoryDTO) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }

        CategoryEntity updatedCategory = CategoryEntity.builder()
                .id(category.get().getId())
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(String id) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }


}
