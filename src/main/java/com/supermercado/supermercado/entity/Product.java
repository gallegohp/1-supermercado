package com.supermercado.supermercado.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio", nullable = false)
    private Double cost;

    @Column(name = "categoria_id", nullable = false)
    private Long categoryId; 

    @Column(name = "stock", nullable = false)
    private Integer stock; 

    @Column(name = "codigo_barras", nullable = false, unique = true)
    @JsonProperty("barcode") 
    private String barcode;

    @Column(name = "activo", nullable = false)
    private Boolean active = true;

    // @ManyToMany
    // @JoinTable(name = "producto_proveedor", joinColumns = @JoinColumn(name =
    // "producto_id"), inverseJoinColumns = @JoinColumn(name = "proveedor_id"))
    // private List<Supplier> suppliers;
    // Para Bryan
}