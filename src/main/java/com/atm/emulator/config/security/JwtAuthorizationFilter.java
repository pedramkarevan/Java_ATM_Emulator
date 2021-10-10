package com.atm.emulator.config.security;


import com.atm.emulator.exception.BaseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter
            .class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService
    ) {
        super(authenticationManager);

    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, ServletException, ServletException, ServletException {

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            String header = request.getHeader(JwtService.TOKEN_HEADER);

            if (StringUtils.isEmpty(header) || !header.startsWith(JwtService.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "token expired");
        }

    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String token = request.getHeader(JwtService.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token)) {
            Jws<Claims> parsedToken = new JwtService().parseToken(token.replace("Bearer ", ""),response);
            Claims username = parsedToken.getBody();
            if (!StringUtils.isEmpty(String.valueOf(username))) {
                return new UsernamePasswordAuthenticationToken(username, null, null);
            }

        }

        return null;
    }


}
