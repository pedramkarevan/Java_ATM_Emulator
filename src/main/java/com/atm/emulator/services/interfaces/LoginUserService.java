package com.atm.emulator.services.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface LoginUserService {
    void  logUserLogin(String userName, HttpServletRequest httpServletRequest);
}
