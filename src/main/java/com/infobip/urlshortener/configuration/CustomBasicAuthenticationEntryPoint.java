package com.infobip.urlshortener.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h2>Custom Basic Authentication Entry Point</h2>
 * <p>
 *     This class customizes BasicAuthenticationEntryPoint
 * </p>
 * <p>
 *     Spring Security Configuration.
 *     See: https://spring.io/projects/spring-security
 * </p>
 */
@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate","Basic realm=" + getRealmName() + "");

        PrintWriter writer = response.getWriter();
        writer.print("HTTP Status 401 - " + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("UrlShortener");
        super.afterPropertiesSet();
    }
}
