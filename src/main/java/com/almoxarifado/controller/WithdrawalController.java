package com.almoxarifado.controller;

import com.almoxarifado.dto.WithdrawalDTO;
import com.almoxarifado.dto.WithdrawalReportDTO;
import com.almoxarifado.service.WithdrawalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/withdrawals")
public class WithdrawalController {
    private final WithdrawalService withdrawalService;

    @GetMapping
    public ResponseEntity<List<WithdrawalReportDTO>> getAllWithdrawalReports() {
        return ResponseEntity.ok(withdrawalService.getAllWithdrawalReports());
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<WithdrawalReportDTO>> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(withdrawalService.findByDateRange(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<WithdrawalDTO> createWithdrawal(@Valid @RequestBody WithdrawalDTO withdrawalDTO) {
        return ResponseEntity.ok(withdrawalService.createWithdrawal(withdrawalDTO));
    }


}
