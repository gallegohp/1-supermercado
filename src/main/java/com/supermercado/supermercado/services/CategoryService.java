package com.supermercado.supermercado.services;

import com.supermercado.supermercado.dto.CategoryRequestDTO;
import com.supermercado.supermercado.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    
    CategoryResponseDTO createCategory(CategoryRequestDTO request);

    CategoryResponseDTO getCategoryById(Long id);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);

    void deleteCategory(Long id);
}
