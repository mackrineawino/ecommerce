package com.ecom.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        // Allow all origins
        headers.add("Access-Control-Allow-Origin", "*");

        // Allow specific methods
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        // Allow specific headers
        headers.add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        // Allow credentials
        headers.add("Access-Control-Allow-Credentials", "true");

        // Expose headers (if needed)
        headers.add("Access-Control-Expose-Headers", "Custom-Header");

        // Max age for caching preflight response
        headers.add("Access-Control-Max-Age", "86400");
    }
}

