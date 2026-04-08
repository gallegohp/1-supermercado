package com.supermercado.supermercado.services;

import com.supermercado.supermercado.dto.SaleRequestDTO;
import com.supermercado.supermercado.dto.SaleResponseDTO;

import java.util.List;

public interface SaleService {

    SaleResponseDTO registerSale(SaleRequestDTO request);

    List<SaleResponseDTO> getAllSales();

    SaleResponseDTO getSaleById(Long id);
}