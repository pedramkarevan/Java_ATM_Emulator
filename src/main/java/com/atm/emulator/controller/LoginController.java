package com.atm.emulator.controller;


import com.atm.emulator.config.security.JwtAuthenticationFilter;
import com.atm.emulator.config.security.JwtRequest;
import com.atm.emulator.config.security.JwtResponse;
import com.atm.emulator.exception.BaseException;
import com.atm.emulator.exception.ErrorCodes;
import com.atm.emulator.model.user.UserEntity;
import com.atm.emulator.model.user.dto.UserDto;
import com.atm.emulator.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;


/**
 * authentication and get token method
 *
 */

@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
     @Autowired
    private UserService userService;



    @PostMapping("/auth/token")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest, HttpServletRequest request, HttpServletResponse response) {


        if(jwtRequest.getCardNo() != null) {
            Optional<UserEntity> optional = userService.findUserByUserName(jwtRequest.getCardNo());
            if (!optional.isPresent())
                throw new BaseException("Card Number Or PIN Is Incorrect", ErrorCodes.ERROR_CODE_CARD_NOT_FOUND);
            //Check For unsuccessful attempts
            if (optional.get().getLoginTriedNumber() != null && optional.get().getLoginTriedNumber() >= JwtAuthenticationFilter.maxTriedNumber) {
                if (optional.get().getLockTime() != null && LocalDateTime.now().minusMinutes(20).isBefore(optional.get().getLockTime()))
                    throw new BaseException("Block Card", ErrorCodes.ERROR_CODE_LOGIN_BLOCKED);
                else if (LocalDateTime.now().minusMinutes(2).isAfter(optional.get().getLockTime()))
                    optional.get().setLoginTriedNumber(0);
            }
            return ResponseEntity.ok().body(jwtAuthenticationFilter.successfulAuthenticationMethod(jwtRequest, request, response, optional.get()));
        }else
            throw new BaseException("Card Number Or PIN Is Incorrect", ErrorCodes.ERROR_CODE_CARD_INCORRECT);


    }







}
