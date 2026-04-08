package com.supermercado.supermercado.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double cost;

    private Integer stock; // Para la Regla de Negocio de validación

    @Column(unique = true, nullable = false)
    private String barcode; // Regla de Negocio 2

    private Boolean active = true;

    //@ManyToMany
    //@JoinTable(name = "producto_proveedor", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "proveedor_id"))
    //private List<Supplier> suppliers;
    // Para Bryan
}