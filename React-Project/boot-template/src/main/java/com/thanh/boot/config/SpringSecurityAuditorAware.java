package com.thanh.boot.config;

import com.thanh.boot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<User> {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityAuditorAware.class);

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logger.warn("No authentication in security context. Now return admin as user for auditing.");
            User admin = new User();
            admin.setId(1L);
            return Optional.of(admin);
        } else {
            // Authentication details was set in JwtAuthenticationFilter
            Long userId = (Long) authentication.getDetails();
            // logger.debug("Returning user ID = {} as user for auditing", userId);
            User user = new User();
            user.setId(userId);
            return Optional.of(user);
        }
    }
}
