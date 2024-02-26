package com.basic.SecurityJwt.SecurityJwt.security;

import com.basic.SecurityJwt.SecurityJwt.model.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrfConf -> csrfConf.disable())
                .sessionManagement(sessionMangConf -> sessionMangConf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/auth/getAuthUsername/{token}").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/orders/create").hasAnyAuthority(Permission.CAN_MANAGE_THE_SHOP.name(), Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/orders/getAll").hasAuthority(Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/shop/getProducts").hasAnyAuthority(Permission.CAN_MANAGE_THE_SHOP.name(), Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/shop/getProductsByType").hasAnyAuthority(Permission.CAN_MANAGE_THE_SHOP.name(), Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/storage/getProducts").hasAnyAuthority(Permission.CAN_MANAGE_THE_SHOP.name(), Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/storage/extractProducts").hasAnyAuthority(Permission.CAN_MANAGE_THE_SHOP.name(), Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/storage/addProducts").hasAuthority(Permission.CAN_MANAGE_THE_STORAGE.name());
                    authConfig.anyRequest().denyAll();
                });

        return httpSecurity.build();
    }

}
