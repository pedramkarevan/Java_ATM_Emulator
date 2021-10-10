package com.atm.emulator.config.security;


import com.atm.emulator.exception.BaseException;
import com.atm.emulator.model.user.UserEntity;
import com.atm.emulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
public class JwtAuthenticationFilter {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    /**
     * Maximum UnSuccessful attempt count
     */
    public static final short maxTriedNumber = 3;

    public JwtResponse successfulAuthenticationMethod(JwtRequest jwtRequest,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      UserEntity currentUser) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtRequest.getCardNo(), jwtRequest.getPin());
            Object object =  authManager.authenticate(authenticationToken).getPrincipal();
            UserEntity user= (UserEntity) object;
            JwtResponse data = new JwtResponse(new JwtService().getToken(user, user.getUsername()));
            response.setContentType("application/json");

             currentUser.setLoginTriedNumber(0);

            return data;

        } catch (Exception e){

            /**
             * Increase the default Maximum unsuccessful attempt ans save in db
             */

            if (currentUser.getLoginTriedNumber() == null)
                 currentUser.setLoginTriedNumber(0);
            currentUser.setLoginTriedNumber(currentUser.getLoginTriedNumber() + 1);
            if (currentUser.getLoginTriedNumber()== maxTriedNumber)
                currentUser.setLockTime(LocalDateTime.now());
            /**
             * We can check which one are incorrect, Pin Or Card No and give a proper response message
             */
            throw new BaseException("Pin or CardNo is incorrect",HttpStatus.UNAUTHORIZED.value());
        }finally{

            userRepository.saveAndFlush(currentUser);
        }

    }


}
