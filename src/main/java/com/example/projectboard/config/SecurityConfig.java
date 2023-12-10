package com.example.projectboard.config;

import com.example.projectboard.dto.UserAccountDto;
import com.example.projectboard.dto.security.BoardPrincipal;
import com.example.projectboard.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/",
                                "/articles",
                                "/articles/search-hashtag"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .build();
    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // static resource로 css, js 등 spring security 의 인증을 거치지 않아도 되는 파일들을 설정
//        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
////        return (web) -> web.ignoring().antMatchers("/css"); 이렇게 할 수도 있지만 위에는 보편적인 파일형식들을 넣어줘서 보다 쉽게 관리할 수 있도록 한다.
//    } ===> 더 이상 권장되지 않은 방식 위에 securityFilterchain 에서 똑같이 형식으로 불러와서 permitAll() 하는 방식으로 관리하는게 권장


    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> userAccountRepository
                .findById(username)
                .map(UserAccountDto::from)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
