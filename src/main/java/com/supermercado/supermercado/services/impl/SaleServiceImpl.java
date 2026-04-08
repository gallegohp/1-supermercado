package com.supermercado.supermercado.services.impl;

import com.supermercado.supermercado.dto.*;
import com.supermercado.supermercado.entity.*;
import com.supermercado.supermercado.repository.*;
import com.supermercado.supermercado.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public SaleResponseDTO registerSale(SaleRequestDTO request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(
                () -> new IllegalArgumentException("Empleado No Encontrado con Id: " + request.getEmployeeId()));

        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());
        sale.setEmployee(employee);

        double cumulativeSubtotal = 0;
        List<SaleDetails> detailsList = new ArrayList<>();

        for (SaleItemDTO itemDTO : request.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId()).orElseThrow(
                    () -> new IllegalArgumentException("Producto no encontrado ID: " + itemDTO.getProductId()));

            if (product.getStock() < itemDTO.getAmount()) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para el producto: '" + product.getName() + "'. Stock disponible: "
                                + product.getStock() + ", cantidad solicitada: " + itemDTO.getAmount());
            }

            product.setStock(product.getStock() - itemDTO.getAmount());
            productRepository.save(product);

            double lineSubtotal = product.getCost() * itemDTO.getAmount();
            cumulativeSubtotal += lineSubtotal;

            SaleDetails detail = new SaleDetails();
            detail.setSale(sale);
            detail.setProduct(product);
            detail.setAmount(itemDTO.getAmount());
            detail.setSubtotal(lineSubtotal);

            detail.setUnitPrice(product.getCost());

            detailsList.add(detail);
        }

        double valueVAT = cumulativeSubtotal * 0.19;
        double totalValue = cumulativeSubtotal + valueVAT;

        sale.setSubtotal(cumulativeSubtotal);
        sale.setVat(valueVAT);
        sale.setTotal(totalValue);
        sale.setDetails(detailsList);

        Sale saveSale = saleRepository.save(sale);

        return constructedResponse(saveSale);
    }

    private SaleResponseDTO constructedResponse(Sale v) {
        SaleResponseDTO response = new SaleResponseDTO();
        response.setId(v.getId());
        response.setDate(v.getDate());
        response.setEmployeeName(v.getEmployee().getName());
        response.setSubtotal(v.getSubtotal());
        response.setVat(v.getVat());
        response.setTotal(v.getTotal());
        return response;
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll().stream()
                .map(this::constructedResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con Id: " + id));
        return constructedResponse(sale);
    }
}
