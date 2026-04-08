package com.supermercado.supermercado.repository;

import com.supermercado.supermercado.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsByNit(String nit);
    Optional<Supplier> findByNit(String nit);
    List<Supplier> findByNameContainingIgnoreCase(String name);
}