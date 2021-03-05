package com.springboot.demo.config;


import com.springboot.demo.filter.AuthenticationFilter;
import com.springboot.demo.handler.AccessDefindHandler;
import com.springboot.demo.handler.LoginExpireHandler;
import com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//开启权限注解控制
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private LoginExpireHandler loginExpireHandler;

    @Autowired
    private AccessDefindHandler accessDefindHandler;

    @Autowired
    private UserService userService;

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
            .csrf().disable()//禁用csrf
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//禁用session
            .and()
            .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()//无需验证的url
            //.antMatchers("/login").permitAll()
            //.antMatchers("/user/info").hasRole("admin")
            .anyRequest()
            .access("@authorityHandler.hasPermission(request,authentication)")//动态权限控制
            .and()
            .formLogin()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(loginExpireHandler)
            .accessDeniedHandler(accessDefindHandler)
            ;

    }
}
