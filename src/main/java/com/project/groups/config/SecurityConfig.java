package com.project.groups.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
public class SecurityConfig extends WebSecurityConfigurerAdapter { //시큐리티 필터

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/homepageimg/**").permitAll() //css등 import를 위해
                    .antMatchers("/main","/login", "/joinFormHJ", "/memberZ/choice*" ).permitAll() //기본 3대장 페이지
                    .antMatchers("/member*").permitAll() //회원가입페이지에서ㅠㅠ
                    .antMatchers("/memberZ/applymember*","/mypage/admmypage*" )
                            .hasRole("ADMIN")
                    .antMatchers("/dataW/dataWRegist*", "/dataW/dataWUpdate*",
                            "/group/groupreg*", "/group/groupList*",
                            "/mypage/tchmypage*","/memberZ/tierchoiceZ*")
                            .hasAnyRole("TEACHER", "TEACHER_BASICTIER", "TEACHER_MASTERTIER", "ADMIN")
                    .antMatchers("/mypage/stdmypage*", "/qnaW/qnaWRegist*", "/qnaW/qnaWBoard*",
                            "/group/userGroupList*", "/dataW/dataWBoardS*", "/dataW/dataWDetail*",
                            "/homework/myhomework*")
                            .hasAnyRole("STUDENT", "ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                    .and()
                .formLogin()
                    .loginPage("/login") //이를 통해서 강제로 로그인페이지를 설정함
                    .failureHandler(new CustomAuthenticationFailureHandler()) //로그인 실패
                    .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                        for (GrantedAuthority authority : authorities) {
                            if (authority.getAuthority().equals("ROLE_STUDENT")) {
                                response.sendRedirect("/group/userGroupList");
                                return;
                            } else if (authority.getAuthority().equals("ROLE_TEACHER") ||
                                        authority.getAuthority().equals("ROLE_TEACHER_BASICTIER") ||
                                        authority.getAuthority().equals("ROLE_TEACHER_MASTERTIER")) {
                                response.sendRedirect("/mypage/tchmypage");
                                return;
                            } else if (authority.getAuthority().equals("ROLE_FREE")) {
                                response.sendRedirect("/main");
                                return;
                            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                response.sendRedirect("/mypage/admmypage");
                                return;
                            }
                        }
                        // 기본적으로는 /main 페이지로 이동
                        response.sendRedirect("/main");
                    }
                })
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login")
                    .permitAll();
    }
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler(){
//        return new CustomAccessDeniedHandler();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

