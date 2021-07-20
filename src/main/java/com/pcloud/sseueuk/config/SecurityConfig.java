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
                // /home 하위의 모든 요청과 /login 요청에 대해 인증권한을 요구하지 않음.
                .antMatchers("/home/**", "/login").permitAll()
                // /api 요청에 대해 ROLE_USER 역할이 있어야 접근할 수 있도록 설정.
                .antMatchers("/api").hasRole("USER")
                // 나머지 요청에 대해서 인증권한이 필요하도록 설정.
                .anyRequest().authenticated()
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
