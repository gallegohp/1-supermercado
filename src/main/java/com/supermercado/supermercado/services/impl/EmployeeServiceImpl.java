package com.supermercado.supermercado.services.impl;

import com.supermercado.supermercado.dto.EmployeeRequestDTO;
import com.supermercado.supermercado.dto.EmployeeResponseDTO;
import com.supermercado.supermercado.entity.Employee;
import com.supermercado.supermercado.entity.Role;
import com.supermercado.supermercado.repository.EmployeeRepository;
import com.supermercado.supermercado.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
        // Valida que no exista otro empleado con la misma cédula
        if (employeeRepository.existsByIdentityCard(request.getIdentityCard())) {
            throw new IllegalArgumentException("Ya existe un empleado con la cédula ingresada");
        }

        Employee employee = Employee.builder()
                .identityCard(request.getIdentityCard())
                .name(request.getName())
                .role(request.getRole())
                .hireDate(request.getHireDate())
                .salary(request.getSalary())
                .build();

        Employee saved = employeeRepository.save(employee);
        return mapToResponse(saved);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));

        // Si la cédula cambió, validar que no exista ya otra igual
        if (!existing.getIdentityCard().equals(request.getIdentityCard()) &&
                employeeRepository.existsByIdentityCard(request.getIdentityCard())) {
            throw new IllegalArgumentException("Ya existe otro empleado con la cédula ingresada");
        }

        existing.setIdentityCard(request.getIdentityCard());
        existing.setName(request.getName());
        existing.setRole(request.getRole());
        existing.setHireDate(request.getHireDate());
        existing.setSalary(request.getSalary());

        Employee updated = employeeRepository.save(existing);
        return mapToResponse(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
             throw new IllegalArgumentException("Empleado no encontrado con ID: " + id);
        }
        // Borrado físico
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));
        return mapToResponse(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesByRole(Role role) {
        return employeeRepository.findByRole(role).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesByHireDateRange(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByHireDateBetween(startDate, endDate).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponseDTO mapToResponse(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .identityCard(employee.getIdentityCard())
                .name(employee.getName())
                .role(employee.getRole())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .build();
    }
}
