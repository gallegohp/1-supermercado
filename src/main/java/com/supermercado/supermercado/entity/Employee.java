package com.supermercado.supermercado.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
// Se especifica el nombre de la tabla referenciando al script data.sql
@Table(name = "empleados") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se mapea cada columna al nombre en español para no romper la BD
    @Column(name = "cedula", nullable = false, unique = true, length = 20)
    private String identityCard; 

    @Column(name = "nombre", nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private Role role; 

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate hireDate; 

    @Column(name = "salario", nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;
}
