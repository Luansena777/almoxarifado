package com.almoxarifado.repository;

import com.almoxarifado.model.Withdrawals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalsRepository extends JpaRepository<Withdrawals, Long> {
}
