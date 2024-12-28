package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.CategoryDTO;
import com.example.TBDBackend.models.Category;
import com.example.TBDBackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> postCategory(@RequestBody CategoryDTO category) {
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody CategoryDTO category) {
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
