package com.atm.emulator.utils;

import com.atm.emulator.model.account.AccountEntity;
import com.atm.emulator.model.bank.BankEntity;
import com.atm.emulator.model.customer.CustomerEntity;
import com.atm.emulator.model.user.UserEntity;
import com.atm.emulator.repository.AccountRepository;
import com.atm.emulator.repository.BankRepository;
import com.atm.emulator.repository.CustomerRepository;
import com.atm.emulator.repository.UserRepository;
import com.atm.emulator.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataSeeder {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @EventListener
    void appReady( ApplicationReadyEvent event) {
        if (!userService.findUserByUserName("6104337861045108").isPresent()) {

            UserEntity user = new UserEntity();
             user.setUsername("6104337861045108");
             user.setPassword(passwordEncoder.encode("1235"));
            user.setEnable(true);
            user.setLoginTriedNumber(0);
            userRepo.save(user);
            CustomerEntity customer =new CustomerEntity();
            customer.setUser(user);
            customer.setFirstName("Pedram");
            customer.setLastName("Karevan");
            customerRepo.save(customer);
            BankEntity bank =new BankEntity();
            bank.setName("Test Bank");
            bankRepository.save(bank);
            AccountEntity account =new AccountEntity();
            account.setBank(bank);
            account.setCustomer(customer);
            account.setBalance(500000L);
            account.setAccountName("Type 1");
            accountRepository.save(account);
        }

    }


}
