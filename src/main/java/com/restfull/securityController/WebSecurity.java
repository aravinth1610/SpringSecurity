package com.restfull.securityController;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter  {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	AuthenticationSuccessHandler controller;
	
	@Autowired
	UserDetailsService service;
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	     
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	  http.authorizeRequests()
    	.antMatchers("/home").authenticated()
        .anyRequest().permitAll()
        .and()
        .rememberMe().tokenRepository(persistentTokenRepository())
        .userDetailsService(this.service)
        .rememberMeCookieName("remember-me-cookie")
        .tokenValiditySeconds(5000)
        .useSecureCookie(true)
        .and()
        .formLogin().loginPage("/login")
            .usernameParameter("gmail").passwordParameter("password")  
            .defaultSuccessUrl("/home")
            .permitAll()
        .and()
        .logout().logoutSuccessUrl("/register").permitAll();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
   }
    
}
