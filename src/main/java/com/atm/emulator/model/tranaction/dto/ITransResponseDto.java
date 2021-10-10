package com.atm.emulator.model.tranaction.dto;


import com.atm.emulator.model.tranaction.TransactionLogEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(types = TransactionLogEntity.class)
public interface ITransResponseDto {
    Long getId();

    @Value("#{target.customer?.firstName} #{target.customer?.lastName}")
    String getCustomerName();

    @Value("#{target.operation}")
    String getOperationName();

   @Value("#{target.insertTime}")
    LocalDateTime getInsertTime();

    Long getAmount();
}
