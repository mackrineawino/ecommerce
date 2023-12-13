package com.ecom.filters;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements Filter {

    Dotenv dotenv = Dotenv.configure().directory("/home/awino/Documents/projects/ecommerce").load();
    String secretKey = dotenv.get("SECRET_KEY");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // Skip token validation for the login endpoint
        if ("/auth/login".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // Extract the token from the Authorization header
        String token = httpRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.getWriter().write("Token missing or invalid");
 
            return;
        }

      

        // Continue with token validation for other URLs
        try {
            // Validate the token
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            
            // You can perform additional validation based on the claims, such as checking roles
            // For example: if (!claims.get("role").equals("USER")) { /* handle unauthorized access */ }

            // Continue with the request
            chain.doFilter(request, response);
        } catch (SignatureException e) {
            // Token is invalid
            response.getWriter().write("Invalid token");
            
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
