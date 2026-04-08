package com.supermercado.supermercado.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplierRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "NIT is required")
    private String nit;

    private String phone;
    private String email;
    private String address;
}