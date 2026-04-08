package com.supermercado.supermercado.services;

import com.supermercado.supermercado.dto.SaleRequestDTO;
import com.supermercado.supermercado.dto.SaleResponseDTO;

public interface SaleService {

    SaleResponseDTO registerSale(SaleRequestDTO request);
} 