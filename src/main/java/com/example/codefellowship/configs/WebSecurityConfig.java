package com.example.codefellowship.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                //allow requests to all urls that match the pattern
                .antMatchers("/", "/api/*", "/login", "/*.css").permitAll()
                .antMatchers(HttpMethod.GET, "/*.css").permitAll()
                //anything else you must be logged in
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/login")
                .defaultSuccessUrl("/myprofile", true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutSuccessUrl("/");

//        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeRequests()
//          .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/*").permitAll()
//                .antMatchers(HttpMethod.GET,"/userprofile/*").permitAll()
//                .antMatchers("/addPost").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/*").permitAll()
//
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/api/profile")
//                .loginPage("/api/login").permitAll()
//                .and()
//                .logout()
//        .and()
//                .rememberMe();


//        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/signup").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/api/login")
//                .defaultSuccessUrl("/profile", true)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }
}