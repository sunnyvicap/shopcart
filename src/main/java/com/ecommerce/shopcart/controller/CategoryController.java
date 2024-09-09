package com.ecommerce.shopcart.controller;


import com.ecommerce.shopcart.dto.CategoryDTO;
import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("public/categories")
    public ResponseEntity<Page<Category>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }

    @PostMapping("admin/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryDTO body) {
        Category category = categoryService.addCategory(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @DeleteMapping("admin/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @PostMapping("admin/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id , @Valid @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(id , category));
    }

}
