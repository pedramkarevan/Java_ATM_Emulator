package com.atm.emulator.model.user.dto;


import com.atm.emulator.model.user.UserEntity;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = UserEntity.class)
public interface IUserPartialDto {
    Long getId();
    String getUsername();
    boolean isEnabled();
    String getFirstName();
    String getLastName();


}
