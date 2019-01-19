package com;
import com.app.service.TSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TSysUserService tSysUserService;
    @Bean
    PasswordEncoder passwordEncoder(){
       return  new PasswordEncoder() {
           @Override
           public String encode(CharSequence charSequence) {
               return charSequence.toString();
           }

           @Override
           public boolean matches(CharSequence charSequence, String s) {
               return charSequence.equals(s);
           }
       };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http.authorizeRequests().antMatchers("/usr/**").authenticated().and().authorizeRequests().anyRequest().permitAll().
               and().formLogin().permitAll();*/

        http.csrf().disable().authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')").
                anyRequest().authenticated().
                and().formLogin().loginPage("/login").successForwardUrl("/admin/toAdminIndex").usernameParameter("username").passwordParameter("password").failureHandler(new ForwardAuthenticationFailureHandler("/errorlogin") {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                if (exception instanceof UsernameNotFoundException) {
                    request.setAttribute("errorMsg", "用户名不正确");
                } else if (exception instanceof BadCredentialsException) {
                    request.setAttribute("errorMsg", "用户名或密码错误");
                } else if (exception instanceof AccountStatusException) {
                    request.setAttribute("errorMsg", "用户名被锁定");
                }
                super.onAuthenticationFailure(request, response, exception);
            }
        }).permitAll();

                  http.addFilterBefore(new LoginAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class );

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /*    //disabled(true) 用户名被禁用
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("123").roles("ADMIN").build();
        //构建一个用户名为tomcat，密码123，角色tomcat
        UserDetails tomcat = User.withDefaultPasswordEncoder().username("tomcat").password("123").roles("tomcat").build();
        //构建一个用户名为user，密码123，角色user
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123").roles("user").build();
        //为内存中保存一个用户
        auth.inMemoryAuthentication().withUser(admin);
        auth.inMemoryAuthentication().withUser(tomcat);
        auth.inMemoryAuthentication().withUser(user);*/
        auth.userDetailsService(tSysUserService);
    }
}