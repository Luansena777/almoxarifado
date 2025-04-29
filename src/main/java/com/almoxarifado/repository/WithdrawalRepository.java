package com.almoxarifado.repository;

import com.almoxarifado.dto.WithdrawalReportDTO;
import com.almoxarifado.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    @Query("SELECT new com.almoxarifado.dto.WithdrawalReportDTO(w.id, w.product.name, w.employee.name, w.quantity, w.withdrawalDate) " +
            "FROM Withdrawal w JOIN w.employee JOIN w.product")
    List<WithdrawalReportDTO> findAllWithdrawalReports();

    @Query("SELECT new com.almoxarifado.dto.WithdrawalReportDTO(w.id, w.product.name, w.employee.name, w.quantity, w.withdrawalDate) " +
            "FROM Withdrawal w JOIN w.employee e JOIN w.product p " +
            "WHERE w.withdrawalDate >= :startDate AND w.withdrawalDate <= :endDate")
    List<WithdrawalReportDTO> findWithdrawalsByDateRangeWithNames(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
