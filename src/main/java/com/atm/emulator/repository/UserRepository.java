package com.atm.emulator.repository;

import com.atm.emulator.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,ICrudRepo<UserEntity> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername( String username);

}
