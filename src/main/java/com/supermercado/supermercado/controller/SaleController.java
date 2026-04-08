package com.supermercado.supermercado.controller;

import com.supermercado.supermercado.dto.SaleRequestDTO;
import com.supermercado.supermercado.dto.SaleResponseDTO;
import com.supermercado.supermercado.services.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> register(@Valid @RequestBody SaleRequestDTO request) {
        SaleResponseDTO response = saleService.registerSale(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}