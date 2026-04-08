package com.supermercado.supermercado.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SaleResponseDTO {
    
    private Long id;
    private LocalDateTime date;
    private String employeeName;
    private Double subtotal;
    private Double vat;
    private Double total;
}
