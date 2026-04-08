package com.supermercado.supermercado.services.impl;

import com.supermercado.supermercado.entity.Product;
import com.supermercado.supermercado.repository.ProductRepository;
import com.supermercado.supermercado.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con Id: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setCost(productDetails.getCost());
        product.setStock(productDetails.getStock());

        product.setBarcode(productDetails.getBarcode());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
       
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setActive(false);

        productRepository.save(product);
    }
}
