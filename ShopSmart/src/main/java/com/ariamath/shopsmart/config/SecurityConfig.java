package com.ariamath.shopsmart.config;

import com.ariamath.shopsmart.security.JwtAuthenticationEntryPoint;
import com.ariamath.shopsmart.security.JwtAuthenticationFilter;
import com.ariamath.shopsmart.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsServiceImpl userDetailsService;

    private JwtAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET,"/ws/**","/ws").permitAll();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/login").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/signup").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/refresh").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/api/auth").hasAnyAuthority("Admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/deny").hasAnyAuthority("Admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/approve").hasAnyAuthority("Admin");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/user/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/user/**").hasAnyAuthority("Admin");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/api/profile/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/profile/**").authenticated();


        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/api/cart/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/api/cart/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/cart/**").authenticated();

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/product/{product_id}").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/product/").hasAnyAuthority("Merchant","Admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/product/{product_id}").hasAnyAuthority("Merchant","Admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/product/{product_id}").hasAnyAuthority("Merchant","Admin");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/**").authenticated();

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/likes/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/likes/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/likes/").authenticated();


        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/comments/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/comments/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/comments/**").authenticated();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/comments/**").authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}