package com.atm.emulator.controller;


import com.atm.emulator.exception.BaseException;
import com.atm.emulator.model.account.dto.AccountPartialDto;
import com.atm.emulator.model.account.dto.IAccountBalanceDto;
import com.atm.emulator.model.customer.CustomerEntity;
import com.atm.emulator.model.tranaction.dto.ITransResponseDto;
import com.atm.emulator.model.user.UserEntity;
import com.atm.emulator.repository.CustomerRepository;
import com.atm.emulator.repository.TransactionRepository;
import com.atm.emulator.repository.UserRepository;
import com.atm.emulator.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/customer/operation")
public class OperationController extends AuthRestController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    TransactionRepository transRepo;

    public Long getCustomerId() {
        UserEntity user = userRepo.getById(getUserId());
        Optional<CustomerEntity> customer = (Optional<CustomerEntity>) customerRepo.findByUserId(user.getId());
        return customer.get().getId();
    }

    @GetMapping("getBalance")
    public ResponseEntity<List<IAccountBalanceDto>>  getBalance() {
        return ResponseEntity.ok(accountService.getCustomerBalance(getCustomerId()));
    }

    @GetMapping("getAllTransaction")
    public ResponseEntity<List<ITransResponseDto>> getTransactionByCustomerId()   {
        return ResponseEntity.ok(transRepo.getAllByCustomerId(getCustomerId()));
    }

    @PutMapping("cashWithdrawal")
    public ResponseEntity<Optional<IAccountBalanceDto>> cashWithdrawal(@Valid @RequestBody AccountPartialDto accountPartialDto){
        return  ResponseEntity.ok(accountService.cashWithdrawal(getCustomerId(),accountPartialDto.getAmount()));
    }

    @PutMapping("cashDeposit")
    public ResponseEntity<Optional<IAccountBalanceDto>> cashDeposit(@Valid @RequestBody AccountPartialDto accountPartialDto){
        return ResponseEntity.ok(accountService.cashDeposit(getCustomerId(),accountPartialDto.getAmount()));
    }

}
