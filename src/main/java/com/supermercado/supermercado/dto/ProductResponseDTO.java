package com.supermercado.supermercado.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String barcode;
    private double cost;
    private Integer stock;
    private Boolean active;
    private String categoryName;
}
