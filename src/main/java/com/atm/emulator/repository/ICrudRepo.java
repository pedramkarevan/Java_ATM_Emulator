package com.atm.emulator.repository;

import com.atm.emulator.model.account.dto.IAccountBalanceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICrudRepo<T>{

     Page<T> findAllBy(Class<T> tClass, Pageable pageable);

     <T> Optional<T> findById(Long id, Class<T> tClass);


}
