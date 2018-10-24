package com.infobip.urlshortener.configuration;

import com.infobip.urlshortener.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <h2>Spring Security Configuration</h2>
 *<p>
 *     This is the configuration for Spring Security - https://spring.io/projects/spring-security
 *</p>
 * <p>
 *     App is secured with Basic Http Authentication
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final static String REALM = "UrlShortener";

    /**
     * {@link com.infobip.urlshortener.services.CustomUserDetailsService}
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Spring Security Configuration.
     * See: https://spring.io/projects/spring-security
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Spring Security Configuration.
     * See: https://spring.io/projects/spring-security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/register","/statistic").authenticated()
                .antMatchers("/account","/help").permitAll()
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin();
    }

    /**
     * Customized Basic Authentication Entry Point
     *
     * @return {@link com.infobip.urlshortener.configuration.CustomBasicAuthenticationEntryPoint}
     */
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    /**
     * Password Encoder
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
