package com.supermercado.supermercado.services;

import com.supermercado.supermercado.dto.EmployeeRequestDTO;
import com.supermercado.supermercado.dto.EmployeeResponseDTO;
import com.supermercado.supermercado.entity.Role;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO request);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request);
    void deleteEmployee(Long id);
    EmployeeResponseDTO getEmployeeById(Long id);
    List<EmployeeResponseDTO> getAllEmployees();
    List<EmployeeResponseDTO> getEmployeesByRole(Role role);
    List<EmployeeResponseDTO> getEmployeesByHireDateRange(LocalDate startDate, LocalDate endDate);
}
