package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.CategoryDTO;
import com.ecommerce.shopcart.exceptions.BadRequestException;
import com.ecommerce.shopcart.exceptions.ResourceNotFoundException;
import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category addCategory(CategoryDTO body) {
        Category category = categoryRepository.findByCategoryName(body.getCategoryName());
        if (category != null) {
            throw new BadRequestException("Category already exists.", "", "");
        }
        category = mapper.map(body, Category.class);
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found.", "" + id, ""));

    }

    public void deleteCategory(Long id) {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category oldCategory = findCategoryById(id);
        oldCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(oldCategory);
    }
}
