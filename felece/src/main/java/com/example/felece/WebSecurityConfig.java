package com.example.felece;

import java.io.IOException;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Resource
    private UserDetailsService userDetailsService;



    @Bean
   public NoOpPasswordEncoder passwordEncoder() {
       return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          System.out.println("asdasd");
            auth.userDetailsService(userDetailsService)
           .passwordEncoder(passwordEncoder());
    }
   


    @Override
	protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()	
        .antMatchers("/user").hasAnyRole("USER")
        .antMatchers("/addTodo").hasAnyRole("USER")
        .antMatchers("/deleteTodo").hasAnyRole("USER")
        .antMatchers("/listTodo").hasAnyRole("USER")
        .antMatchers("/admin").hasAnyRole("ADMIN")
        .antMatchers("/list").hasAnyRole("ADMIN")
        .antMatchers("/add").hasAnyRole("ADMIN")
        .antMatchers("/update").hasAnyRole("ADMIN")
        .antMatchers("/update").hasAnyRole("ADMIN")			
        .anyRequest().authenticated()
				.and()
      .formLogin()
        .loginPage("/login")
        .permitAll()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginProcessingUrl("/doLogin")
        .defaultSuccessUrl("/",true);
        
  }
}
