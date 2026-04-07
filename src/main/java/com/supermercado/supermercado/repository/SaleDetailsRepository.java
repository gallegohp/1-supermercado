package com.supermercado.supermercado.repository;

import com.supermercado.supermercado.entity.SaleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {
    
}
