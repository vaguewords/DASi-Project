package com.project.dasi.auth.config;

import com.project.dasi.auth.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity        //spring security 를 적용한다는 Annotation
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests()
                .antMatchers("/resources/*").permitAll()
                // USER, ADMIN 접근 허용
                .antMatchers("/content/mypage/*").hasRole("USER")
                .antMatchers("/admin/*").hasRole("ADMIN");


            http.formLogin()
                .loginPage("/content/member/login")
                .defaultSuccessUrl("/")
                //.failureHandler(authFailHandler)
                .usernameParameter("userId")
                .passwordParameter("password")
                .and()
                .logout()
                //.logoutRequestMatcher(new AntPathRequestMatcher("/"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                //.logoutSuccessUrl("/")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/fail")
                .maxSessionsPreventsLogin(true);
        // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
        return http.build();
    }


}