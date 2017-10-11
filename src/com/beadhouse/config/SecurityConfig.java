package com.beadhouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by beadhouse on 2017/1/10.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/userregister").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/user/login").failureUrl("/login?error")
                .defaultSuccessUrl("/user")
                .usernameParameter("userName")
                .usernameParameter("password")
                .permitAll()
                .and().logout()
                .logoutUrl("/loginout").logoutSuccessUrl("/login?loggedout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .permitAll()
                .and().csrf().disable();
    }
}
