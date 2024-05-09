package com.ariamath.shopsmart.security;

public class SecurityConfiguration {
    /*
    private UserDetailsService userDetailsService ;
    private PasswordEncoder passwordEncoder ;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //For authentication, this will tell Spring where to look for users(userDetailService) and whatpassword encoder to use.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");


        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/ws",
                        "/ws/**"
                )
                .permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll(); // this will not secure any path you specify
        http.authorizeRequests().antMatchers("/api/signup").permitAll();
        http.authorizeRequests().antMatchers("/api/user/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/signup/approve").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers("/api/post/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_STUDENT, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE);
        http.authorizeRequests().antMatchers("/api/profile/**")
                .hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE, RoleUtil.ROLE_STUDENT);
        http.authorizeRequests().antMatchers("/api/follow/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/announcement/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/announcement/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN);
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/announcement/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/announcement/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN);

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/requestinfo/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN);
        http.authorizeRequests().antMatchers("/api/comment/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/scholarshipJob/**").authenticated();
        http.authorizeRequests().antMatchers("/api/scholarshipJob/*").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE);
        http.authorizeRequests().antMatchers("/api/scholarshipJob/appeal/**").authenticated();
        http.authorizeRequests().antMatchers("/api/rating/**")
                .hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE, RoleUtil.ROLE_STUDENT);

        //report usecase
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/report").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/report").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/report/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/report/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/report/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/report/ban/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/report/unban/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN);

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/search").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE, RoleUtil.ROLE_STUDENT);
        //--------------


        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/product/**").authenticated();


        //--------------

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll(); // this will not secure any path you specify
        http.authorizeRequests().antMatchers("/api/signup").permitAll();
        http.authorizeRequests().antMatchers("/api/user/**").authenticated();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/product/**").authenticated();


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    */
}
