package com.atm.emulator.repository;

import com.atm.emulator.model.account.AccountEntity;

import com.atm.emulator.model.account.dto.IAccountBalanceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>,ICrudRepo<AccountEntity> {
    @Query("select a from AccountEntity a  where a.customer.id=?1 ")
    List<IAccountBalanceDto> getCustomerBalance(Long customerId, Class<IAccountBalanceDto> tClass);
    AccountEntity findByCustomerId( Long customerId);


}
