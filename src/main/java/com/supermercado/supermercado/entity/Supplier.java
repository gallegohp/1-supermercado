package com.supermercado.supermercado.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nit", nullable = false, unique = true, length = 20)
    private String nit;

    @Column(name = "nombre", nullable = false, length = 150)
    private String name;

    @Column(name = "telefono", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "direccion", length = 255)
    private String address;

    @ManyToMany(mappedBy = "suppliers")
    @ToString.Exclude    
    @EqualsAndHashCode.Exclude  
    @Builder.Default
    private Set<Product> products = new HashSet<>();
}