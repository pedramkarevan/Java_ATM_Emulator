package com.atm.emulator.repository;

import com.atm.emulator.model.bank.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository  extends JpaRepository<BankEntity, Long>,ICrudRepo<BankEntity> {
}
