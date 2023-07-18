package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    public UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    //Implementing jwt manually instead of using application.properties

    // doing the encryption of password
    @Bean
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }

    // creating roles for different user
//    @Bean
//    public UserDetailsService userdetailsservice(){
//        UserDetails anshu = User.builder().username("anshu").password(passwordencoder().encode("anshu")).roles("ADMIN").build();
//        UserDetails ansh = User.builder().username("ansh").password(passwordencoder().encode("ansh")).roles("User").build();
//        return new InMemoryUserDetailsManager(ansh,anshu);
//    }

    // setting up the security chain
    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET,"/api/**")
                        .permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
