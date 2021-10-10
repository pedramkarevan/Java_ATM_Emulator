package com.atm.emulator.model.account.dto;


import com.atm.emulator.model.account.AccountEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(types = AccountEntity.class)
public interface IAccountBalanceDto {

    @Value("#{target.balance} ")
    long getBalance();

    @Value("#{target.customer?.firstName} #{target.customer?.lastName}")
    String getCustomerName();

    LocalDateTime getUpdateTime();

}
