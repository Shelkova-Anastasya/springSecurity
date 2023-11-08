package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.itmentor.spring.boot_security.demo.DAO.UserRepositoryImp;

import static ru.itmentor.spring.boot_security.demo.model.Role.ADMIN;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

private final SuccessUserHandler successUserHandler;

    private final UserRepositoryImp userRepositoryImp;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserRepositoryImp userRepositoryImp) {
        this.successUserHandler = successUserHandler;
       this.userRepositoryImp = userRepositoryImp;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userRepositoryImp).passwordEncoder(passwordEncoder());
    }




    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/user").hasAnyAuthority("USER","ADMIN" )
                .antMatchers("/admin**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") // Укажите URL для выхода
                .logoutSuccessUrl("/login") // Укажите URL, на который перенаправить после выхода
                .permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}