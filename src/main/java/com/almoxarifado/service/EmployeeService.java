package com.almoxarifado.service;

import com.almoxarifado.model.Employee;
import com.almoxarifado.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> showEmployees() {
        return employeeRepository.findAll();
    }




}
