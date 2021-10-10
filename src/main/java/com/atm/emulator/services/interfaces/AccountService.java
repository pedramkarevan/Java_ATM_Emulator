package com.atm.emulator.services.interfaces;



import com.atm.emulator.model.account.AccountEntity;
import com.atm.emulator.model.account.dto.IAccountBalanceDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<IAccountBalanceDto> getCustomerBalance(Long userId);
    Optional<IAccountBalanceDto> cashWithdrawal(Long userId, Long amount);
    Optional<IAccountBalanceDto> cashDeposit(Long userId, Long amount);

}
