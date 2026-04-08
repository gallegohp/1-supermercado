package com.supermercado.supermercado.services;

import com.supermercado.supermercado.dto.*;
import com.supermercado.supermercado.entity.Product;
import com.supermercado.supermercado.entity.StockEntryDTO;
import com.supermercado.supermercado.entity.Supplier;
import com.supermercado.supermercado.repository.ProductRepository;
import com.supermercado.supermercado.repository.SupplierRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public SupplierService(SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }


    public SupplierResponseDTO create(SupplierRequestDTO request) {
        if (supplierRepository.existsByNit(request.getNit())) {
            throw new RuntimeException("A supplier with NIT " + request.getNit() + " already exists");
        }

        Supplier supplier = new Supplier();
        supplier.setNit(request.getNit());
        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());

        return mapToDTO(supplierRepository.save(supplier));
    }

    public List<SupplierResponseDTO> getAll() {
        return supplierRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public SupplierResponseDTO getById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return mapToDTO(supplier);
    }

    public SupplierResponseDTO update(Long id, SupplierRequestDTO request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        if (!supplier.getNit().equals(request.getNit()) && supplierRepository.existsByNit(request.getNit())) {
            throw new RuntimeException("A supplier with NIT " + request.getNit() + " already exists");
        }

        supplier.setNit(request.getNit());
        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());

        return mapToDTO(supplierRepository.save(supplier));
    }

    public void delete(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }


    public List<SupplierResponseDTO> searchByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public SupplierResponseDTO getByNit(String nit) {
        Supplier supplier = supplierRepository.findByNit(nit)
                .orElseThrow(() -> new RuntimeException("Supplier not found with NIT: " + nit));
        return mapToDTO(supplier);
    }

    public void stockEntry(StockEntryDTO request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + request.getSupplierId()));

        product.getSuppliers().add(supplier);

        product.setStock(product.getStock() + request.getQuantity());

        productRepository.save(product);
    }


    private SupplierResponseDTO mapToDTO(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(supplier.getId());
        dto.setNit(supplier.getNit());
        dto.setName(supplier.getName());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());
        return dto;
    }
}