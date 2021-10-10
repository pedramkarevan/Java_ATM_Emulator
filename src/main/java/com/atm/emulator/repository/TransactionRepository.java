package com.atm.emulator.repository;

import com.atm.emulator.model.tranaction.TransactionLogEntity;
import com.atm.emulator.model.tranaction.dto.ITransResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionLogEntity, Long>,ICrudRepo<TransactionLogEntity> {
    TransactionLogEntity findByCustomerId( Long customerId);
    List<ITransResponseDto> getAllByCustomerId(Long customerId);
}
