package com.almoxarifado.service;

import com.almoxarifado.dto.WithdrawalDTO;
import com.almoxarifado.dto.WithdrawalReportDTO;
import com.almoxarifado.exception.EmployeeNotFoundException;
import com.almoxarifado.exception.ProductNotFoundException;
import com.almoxarifado.model.Employee;
import com.almoxarifado.model.Product;
import com.almoxarifado.model.Withdrawal;
import com.almoxarifado.repository.EmployeeRepository;
import com.almoxarifado.repository.ProductRepository;
import com.almoxarifado.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    public List<WithdrawalReportDTO> getAllWithdrawalReports() {
        return withdrawalRepository.findAllWithdrawalReports();
    }

    public WithdrawalDTO createWithdrawal(WithdrawalDTO withdrawalDTO) {
        Product product = productRepository.findById(withdrawalDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        Employee employee = employeeRepository.findById(withdrawalDTO.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado"));

        if (product.getQuantity() < withdrawalDTO.getQuantity()) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }

        Withdrawal withdrawal = Withdrawal.builder()
                .product(product)
                .employee(employee)
                .quantity(withdrawalDTO.getQuantity())
                .withdrawalDate(LocalDateTime.now())
                .build();

        product.setQuantity(product.getQuantity() - withdrawal.getQuantity());
        productRepository.save(product);

        withdrawal = withdrawalRepository.save(withdrawal);

        WithdrawalDTO savedWithdrawalDTO = new WithdrawalDTO();
        BeanUtils.copyProperties(withdrawal, withdrawalDTO);
        savedWithdrawalDTO.setProductId(withdrawal.getProduct().getId());
        savedWithdrawalDTO.setEmployeeId(withdrawal.getEmployee().getId());

        return savedWithdrawalDTO;

    }


    public List<WithdrawalReportDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("As datas de início e fim não podem ser nulas.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }

        return withdrawalRepository.findWithdrawalsByDateRangeWithNames(startDate, endDate);

    }

}
