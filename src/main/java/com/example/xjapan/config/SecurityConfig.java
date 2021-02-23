package com.example.xjapan.config;

import com.example.xjapan.security.handler.ClubLoginSuccessHandler;
import com.example.xjapan.security.service.ClubUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


/*    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$TdxDxaSnwEV.3yiN8e0rf.QclYOeL3QwCW9nH7FP/AuXhZc9abec6")
                .roles("USER");
    }*/

    @Override
    protected void configure(HttpSecurity http)throws Exception{

       http.authorizeRequests()
                .antMatchers("/main/main").hasRole("USER");

       http.formLogin();

       http.csrf().disable();
       http.logout();
       http.oauth2Login();
       http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService);
    }

}

