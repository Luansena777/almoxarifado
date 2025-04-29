package com.almoxarifado.controller;

import com.almoxarifado.dto.EmployeeDTO;
import com.almoxarifado.model.Employee;
import com.almoxarifado.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> showEmployees() {
        return ResponseEntity.ok(employeeService.showEmployees());
    }

    @GetMapping("/{registration}")
    public ResponseEntity<Employee> findByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok(employeeService.findByRegistration(registration));

    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.create(employeeDTO));
    }

    @PutMapping("/{registration}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String registration, @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(registration, employeeDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{registration}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String registration) {
        employeeService.deleteEmployee(registration);
        return ResponseEntity.noContent().build();
    }


}
