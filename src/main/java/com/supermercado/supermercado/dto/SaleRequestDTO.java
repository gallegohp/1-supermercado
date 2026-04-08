package com.supermercado.supermercado.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class SaleRequestDTO {
    
    @NotNull(message = "El Id del Empleado es obligatorio")
    private Long employeeId;

    @NotEmpty(message = "La Venta debe tener al menos Un Producto")
    private List<SaleItemDTO> items;
}
