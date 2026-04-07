package com.supermercado.supermercado.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "ventas")
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double vat;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<SaleDetails> details;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }
}

