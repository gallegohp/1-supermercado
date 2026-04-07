package com.supermercado.supermercado.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalle_venta")
@Data
public class SaleDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer amount;

    @Column(name = "precio_unit", nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double subtotal;
}
