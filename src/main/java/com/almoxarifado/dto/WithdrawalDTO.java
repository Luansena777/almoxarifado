package com.almoxarifado.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WithdrawalDTO {
    private Long id;

    @NotNull(message = "O ID do produto é obrigatório")
    private Long productId;

    @NotNull(message = "O ID do funcionário é obrigatório")
    private Long employeeId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    private Integer quantity;

    private LocalDateTime withdrawalDate;
}
