package com.pcloud.sseueuk.config;

import com.pcloud.sseueuk.service.oauth.CustomOAuth2UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/templates/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/home/**", "/login")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
            .oauth2Login().loginPage("/login")
            .userInfoEndpoint()
            .userService(customOAuth2UserService);
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout();
    }
}
