package com.atm.emulator.model.customer;



import com.atm.emulator.model.account.AccountEntity;
import com.atm.emulator.model.core.IEntity;
import com.atm.emulator.model.tranaction.TransactionLogEntity;
import com.atm.emulator.model.user.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class CustomerEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNo")
    private String phoneNo;

    @OneToOne(optional = false)
    private UserEntity user;


    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<AccountEntity> account;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<TransactionLogEntity> transactionLogs;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




    public List<AccountEntity> getAccount() {
        return account;
    }

    public void setAccount(List<AccountEntity> account) {
        this.account = account;
    }



    public List<TransactionLogEntity> getTransactionLogs() {
        return transactionLogs;
    }

    public void setTransactionLogs(List<TransactionLogEntity> transactionLogs) {
        this.transactionLogs = transactionLogs;
    }



    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
