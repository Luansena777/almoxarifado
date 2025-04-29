package com.almoxarifado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WithdrawalReportDTO {
    private Long id;
    private String productName;
    private String employeeName;
    private Integer quantity;
    private LocalDateTime withdrawalTime;
}
