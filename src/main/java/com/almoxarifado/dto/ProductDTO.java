package com.almoxarifado.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    @NotNull(message = "O código é obrigatório")
    private String code;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String category;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantity;

}
