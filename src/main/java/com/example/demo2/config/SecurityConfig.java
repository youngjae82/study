package com.example.demo2.config;

import com.example.demo2.service.FrameService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    private final PasswordEncoder passwordEncoder;
    private final FrameService frameService;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
        사용자 인증 서비스 설정을 이곳에서 수행.
        인메모리, 관계형 데이터베이스, LDAP, 사용자정의 등으로 설정가능.
        여기서는 userDetailService 를 직접구현한 사용자정의 인증서비스를 수행한다.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(frameService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       //http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/xframe/**").permitAll();
//                //.anyRequest().permitAll();
//    }
}
