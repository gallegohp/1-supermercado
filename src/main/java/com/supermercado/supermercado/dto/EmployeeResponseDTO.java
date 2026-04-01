package com.supermercado.supermercado.dto;

import com.supermercado.supermercado.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
    private Long id;
    private String identityCard;
    private String name;
    private Role role;
    private LocalDate hireDate;
    private BigDecimal salary;
}
