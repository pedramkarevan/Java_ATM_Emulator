package com.atm.emulator.services.implementations;


import com.atm.emulator.aspect.AccountOperation;
import com.atm.emulator.exception.BaseException;
import com.atm.emulator.exception.ErrorCodes;
import com.atm.emulator.model.account.AccountEntity;
import com.atm.emulator.model.account.dto.IAccountBalanceDto;
import com.atm.emulator.repository.AccountRepository;
import com.atm.emulator.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;



    @Override
    public List<IAccountBalanceDto> getCustomerBalance(Long customerId) {

        return accountRepository.getCustomerBalance(customerId, IAccountBalanceDto.class);
    }

    @Override
    @AccountOperation(operation = "cashWithdrawal")
    @Transactional
    public Optional<IAccountBalanceDto> cashWithdrawal(Long customerId, Long amount) {
        AccountEntity account =accountRepository.findByCustomerId(customerId);
        if (account.getBalance(500000L)-amount >0) {
            account.setBalance(account.getBalance(500000L) - amount);
            account.setUpdateTime(LocalDateTime.now());
            accountRepository.saveAndFlush(account);
        }else throw new BaseException("Account balance error", ErrorCodes.ERROR_CODE_BALANCE_ERROR);
        return accountRepository.findById(account.getId(),IAccountBalanceDto.class);
    }

    @Override
    @AccountOperation(operation = "cashDeposit")
    @Transactional
    public Optional<IAccountBalanceDto> cashDeposit(Long customerId, Long amount) {
        AccountEntity account =accountRepository.findByCustomerId(customerId);
        account.setBalance(account.getBalance(500000L)+amount);
        account.setUpdateTime(LocalDateTime.now());
        accountRepository.saveAndFlush(account);
        return accountRepository.findById(account.getId(), IAccountBalanceDto.class);
    }



}
