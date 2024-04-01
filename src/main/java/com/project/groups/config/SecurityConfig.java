package com.project.groups.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
public class SecurityConfig extends WebSecurityConfigurerAdapter { //시큐리티 필터

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
//                .antMatchers("/**").authenticated() //모든 경로에 인증이 필요함
//                .antMatchers("/admin").access("hasRole("ROLE_ADMIN")") /admin 경로는 ROLE ADMIN 이 필요하다
                .anyRequest().permitAll();
//                .and().formLogin().loginPage("/loginForm"); //이를 통해서 강제로 로그인페이지를 설정함
    }
}
