package com.thanh.boot.security;

import com.thanh.boot.entity.User;
import com.thanh.boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JpaAuthenticationProvider.class);

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();

        Optional<User> optUser = userService.findByUsername(username);
        if (optUser.isPresent()) {
            User user = optUser.get();

            String encryptedPassword = user.getPassword();
            boolean passwordsMatch = passwordEncoder.matches(password, encryptedPassword);
            if (passwordsMatch) {
                boolean enabled = user.isEnabled();
                if (enabled) {
                    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(username, "[Password]", authorities);
                    authResult.setDetails(user.getId());
                    return authResult;
                } else {
                    logger.debug("User is disabled: {}", username);
                    throw new DisabledException("User " + username + " is disabled");
                }
            } else {
                logger.debug("Incorrect password for user: {}", username);
                throw new BadCredentialsException("Incorrect password");
            }

        } else {
            logger.debug("User not found: {}", username);
            throw new UsernameNotFoundException(username);
        }
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UsernamePasswordAuthenticationToken.class;
    }
}
