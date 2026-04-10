package com.supermercado.supermercado.services.impl;

import com.supermercado.supermercado.dto.CategoryRequestDTO;
import com.supermercado.supermercado.dto.CategoryResponseDTO;
import com.supermercado.supermercado.dto.ProductResponseDTO;
import com.supermercado.supermercado.entity.Category;
import com.supermercado.supermercado.exception.DuplicateResourceException;
import com.supermercado.supermercado.exception.ResourceNotFoundException;
import com.supermercado.supermercado.repository.CategoryRepository;
import com.supermercado.supermercado.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Ya existe una categoría con ese nombre");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category saved = categoryRepository.save(category);
        return mapToResponse(saved);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));

        return mapToResponse(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));

        if (!existing.getName().equals(request.getName()) &&
                categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Ya existe una categoría con ese nombre");
        }

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());

        Category updated = categoryRepository.save(existing);
        return mapToResponse(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }

        categoryRepository.deleteById(id);
    }

    private CategoryResponseDTO mapToResponse(Category category) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());

        if (category.getProducts() != null) {
            List<ProductResponseDTO> products = category.getProducts()
                    .stream()
                    .filter(p -> p.getActive())
                    .map(p -> {
                        ProductResponseDTO pr = new ProductResponseDTO();
                        pr.setId(p.getId());
                        pr.setName(p.getName());
                        pr.setBarcode(p.getBarcode());
                        pr.setCost(p.getCost());
                        pr.setStock(p.getStock());
                        pr.setActive(p.getActive());
                        pr.setCategoryName(category.getName());
                        return pr;
                    })
                    .collect(Collectors.toList());

            response.setProducts(products);
        }

        return response;
    }
}