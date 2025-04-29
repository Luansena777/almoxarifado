package com.almoxarifado.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "A matrícula é obrigatória")
    private String registration;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String department;
}
