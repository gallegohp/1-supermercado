package com.supermercado.supermercado.controller;

import com.supermercado.supermercado.dto.EmployeeRequestDTO;
import com.supermercado.supermercado.dto.EmployeeResponseDTO;
import com.supermercado.supermercado.entity.Role;
import com.supermercado.supermercado.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
// Mantenemos la URL en español para las llamadas al endpoint según el modelo
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO request) {
        return new ResponseEntity<>(employeeService.createEmployee(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id, 
            @Valid @RequestBody EmployeeRequestDTO request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/cargo/{role}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByRole(@PathVariable Role role) {
        return ResponseEntity.ok(employeeService.getEmployeesByRole(role));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByHireDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(employeeService.getEmployeesByHireDateRange(start, end));
    }
}
