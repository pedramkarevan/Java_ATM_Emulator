package com.atm.emulator.repository;

import com.atm.emulator.model.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,ICrudRepo<CustomerEntity> {


    Optional<CustomerEntity> findByUserId(Long user_id);

}
