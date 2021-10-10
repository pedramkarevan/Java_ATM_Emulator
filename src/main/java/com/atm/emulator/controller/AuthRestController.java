package com.atm.emulator.controller;


import com.atm.emulator.repository.UserRepository;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;



@RestController
abstract public class AuthRestController {
    @Autowired
    private UserRepository userRepo ;

    /**
     *
     *
     * @return      the id of user/card holder entity
     *
     */
    protected Long getUserId() {
        DefaultClaims x = (DefaultClaims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.parseLong(x.getSubject());

    }




}
