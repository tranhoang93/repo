package com.thanh.boot.security;

import com.thanh.boot.config.ApplicationProperties;
import com.thanh.boot.service.LoginSessionService;
import com.thanh.boot.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MessageSource messageSource;
    private JpaAuthenticationProvider jpaAuthenticationProvider;
    private LoginSessionService loginSessionService;
    private ApplicationProperties applicationProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(jpaAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(Constants.LOGIN_URL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedAuthenticationEntryPoint());
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean(JwtAuthenticationFilter jwtAuthenticationFilter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registrar = new FilterRegistrationBean<>(jwtAuthenticationFilter);
        registrar.setEnabled(false);
        return registrar;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter filter = new LoginFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setKeyPairProvider(keyPairProvider());
        filter.setMessageSource(messageSource);
        filter.setLoginSessionService(loginSessionService);
        filter.setApplicationProperties(applicationProperties);
        return filter;
    }

    @Bean
    public RefreshTokenFilter refreshTokenFilter() throws Exception {
        RefreshTokenFilter filter = new RefreshTokenFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setKeyPairProvider(keyPairProvider());
        filter.setMessageSource(messageSource);
        return filter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setKeyPairProvider(keyPairProvider());
        filter.setMessageSource(messageSource);
        return filter;
    }

    @Bean
    public KeyPairProvider keyPairProvider() {
        return new StaticKeyPairProvider();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setJpaAuthenticationProvider(JpaAuthenticationProvider jpaAuthenticationProvider) {
        this.jpaAuthenticationProvider = jpaAuthenticationProvider;
    }

    @Autowired @Lazy
    public void setLoginSessionService(LoginSessionService loginSessionService) {
        this.loginSessionService = loginSessionService;
    }

    @Autowired @Lazy
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
}
