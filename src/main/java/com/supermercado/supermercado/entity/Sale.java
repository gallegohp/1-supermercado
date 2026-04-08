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

    @Column(name = "fecha", nullable = false)
    private LocalDateTime date;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "iva", nullable = false)
    private Double vat;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetails> details;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }
}