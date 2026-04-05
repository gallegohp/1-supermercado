package com.supermercado.supermercado.repository;

import com.supermercado.supermercado.entity.Role;
import com.supermercado.supermercado.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    boolean existsByIdentityCard(String identityCard);

    List<Employee> findByRole(Role role);

    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);
}
