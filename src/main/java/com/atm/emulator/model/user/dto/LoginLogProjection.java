package com.atm.emulator.model.user.dto;


import com.atm.emulator.model.user.UserEntity;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = UserEntity.class)
public interface LoginLogProjection {
    String getUsername();
}
