package com.almoxarifado.repository;

import com.almoxarifado.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByRegistration(String registration);
    Optional<Employee> findByRegistration(String registration);
}
