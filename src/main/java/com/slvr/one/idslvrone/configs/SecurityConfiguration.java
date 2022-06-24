package com.slvr.one.idslvrone.configs;

import com.slvr.one.idslvrone.services.AuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthManager appAuthManager() { return new AuthManager(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().ignoringAntMatchers("/api/auth")
            .and()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/auth").permitAll()
                .anyRequest().authenticated()
            .and()
                .httpBasic(withDefaults())
                .authenticationManager(appAuthManager());
        return http.build();
    }
}
