package com.atm.emulator.services.interfaces;



import com.atm.emulator.model.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {

    public List<UserEntity> getAll();
    public boolean userExist(UserEntity user);
    public Optional<UserEntity> findUserByUserName(String userName);
    public UserEntity findLoggedInUser(Principal principal);
    public UserEntity saveUser(UserEntity user, String password);


}
