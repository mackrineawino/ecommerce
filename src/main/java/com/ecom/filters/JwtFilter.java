package com.ecom.filters;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class JwtFilter implements Filter {

    
    String secretKey = "w9M7K/D9ZqNyAnG3vlY5VmQhlmjofT2mRgWt9J+fJWg=";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        System.out.println("getting......" + requestURI);


        // Skip token validation for the login endpoint
        if ("/ecommerce/rest/auth/login".equals(requestURI) || "/ecommerce/rest/auth/register".equals(requestURI) || "/ecommerce/stripe/callback".equals(requestURI) || "/ecommerce/login".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // Extract the token from the Authorization header
        String authorizationHeader = httpRequest.getHeader("Authorization");
        System.out.println(" Token: " + authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.getWriter().write("Token missing or invalid");
            return;
        }

        // Extract the actual token value from the "Bearer " prefix and remove spaces
        String token = authorizationHeader.substring(7).replaceAll("\\s", "");

        // Log the extracted token for debugging
        System.out.println("Extracted Token: " + token);

        // Continue with token validation for other URLs
        try {
            // Validate the token
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

            // Additional validation if needed

            // Continue with the request
            chain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            // Log the exception for diagnosis
            e.printStackTrace();

            // Token is malformed
            response.getWriter().write("Malformed token");
        } catch (SignatureException e) {
            // Log the exception for diagnosis
            e.printStackTrace();

            // Token signature is invalid
            response.getWriter().write("Invalid token signature");
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
