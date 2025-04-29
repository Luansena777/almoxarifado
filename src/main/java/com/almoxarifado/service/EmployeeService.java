package com.almoxarifado.service;

import com.almoxarifado.dto.EmployeeDTO;
import com.almoxarifado.exception.DuplicateRegistrationException;
import com.almoxarifado.exception.EmployeeNotFoundException;
import com.almoxarifado.model.Employee;
import com.almoxarifado.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDTO> showEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    BeanUtils.copyProperties(employee, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Employee findByRegistration(String registration) {
        return employeeRepository.findByRegistration(registration)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado com matrícula: " + registration));

    }

    @Transactional
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByRegistration(employeeDTO.getRegistration())) {
            throw new DuplicateRegistrationException("Já existe um funcionário com esta matrícula");
        }

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee = employeeRepository.save(employee);

        EmployeeDTO savedEmployeeDto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);

        return savedEmployeeDto;
    }

    @Transactional
    public void updateEmployee(String registration, EmployeeDTO employeeDTO) {
        Employee existingEmployee = findByRegistration(registration);

        if (existingEmployee == null) {
            throw new EmployeeNotFoundException("Funcionário não encontradado com o registro " + registration);
        }

        if (existingEmployee.getName() == null || existingEmployee.getRegistration() == null) {
            throw new IllegalArgumentException("Nome e o Registro são obrgatórios");
        }

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setDepartment(employeeDTO.getDepartment());
        existingEmployee.setRegistration(employeeDTO.getRegistration());

        employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(String registration) {
        if (!employeeRepository.existsByRegistration(registration)) {
            throw new EmployeeNotFoundException("Funcionário não encontrado com matrícula: " + registration);
        }
        employeeRepository.delete(findByRegistration(registration));
    }
}
