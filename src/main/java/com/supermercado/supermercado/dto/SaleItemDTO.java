package com.supermercado.supermercado.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SaleItemDTO {
    
    @NotNull(message = "El Id del Producto es obligatorio")
    private Long productId;

    @NotNull(message = "La Cantidad de Productos es obligatoria")
    @Positive(message = "La Cantidad de Productos debe ser Mayor a cero")
    private Integer amount;
}
