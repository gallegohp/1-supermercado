package com.supermercado.supermercado.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StockEntryDTO {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}