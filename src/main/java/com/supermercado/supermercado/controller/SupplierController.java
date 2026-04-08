package com.supermercado.supermercado.controller;

import com.supermercado.supermercado.dto.*;
import com.supermercado.supermercado.services.SupplierService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    

    @PostMapping("/suppliers")
    public ResponseEntity<SupplierResponseDTO> create(@Valid @RequestBody SupplierRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.create(request));
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierResponseDTO>> getAll() {
        return ResponseEntity.ok(supplierService.getAll());
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getById(id));
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody SupplierRequestDTO request) {
        return ResponseEntity.ok(supplierService.update(id, request));
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/suppliers/search")
    public ResponseEntity<List<SupplierResponseDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.searchByName(name));
    }

    @GetMapping("/suppliers/nit/{nit}")
    public ResponseEntity<SupplierResponseDTO> getByNit(@PathVariable String nit) {
        return ResponseEntity.ok(supplierService.getByNit(nit));
    }


   
}