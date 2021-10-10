package com.atm.emulator.model.bank;


import com.atm.emulator.model.account.AccountEntity;
import com.atm.emulator.model.core.IEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banks")
public class BankEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "bank",fetch=FetchType.LAZY)
    private List<AccountEntity> account;

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountEntity> getAccount() {
        return account;
    }

    public void setAccount(List<AccountEntity> account) {
        this.account = account;
    }
}
