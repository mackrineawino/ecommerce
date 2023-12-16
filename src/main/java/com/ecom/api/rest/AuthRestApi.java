package com.ecom.api.rest;

import com.ecom.app.bean.AuthBeanI;
import com.ecom.app.bean.UserBeanI;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.User;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/auth")
public class AuthRestApi extends BaseRestApi {

    @Inject
    private AuthBeanI authBean;

    @EJB
    private UserBeanI userBean;


    Dotenv dotenv = Dotenv.configure().directory("/home/awino/Documents/projects/ecommerce").load();
    String secretKey = dotenv.get("SECRET_KEY");

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        userBean.addOrUpdate(user);
        
        return respond();

    }
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(userBean.list(User.class));
    }
    
 @RolesAllowed("LOGGED_IN")
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        userBean.delete(User.class, id);
        return respond();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User loginUser) {
        try {
            System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            User authenticatedUser = authBean.authenticate(loginUser);
    
            if (authenticatedUser != null) {
                // Generate JWT token
                String token = Jwts.builder()
                        .setSubject(authenticatedUser.getUsername())
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();
    
                // Create a response object with user information and token
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("user", authenticatedUser);
                responseData.put("token", token);
    System.out.println(token);
                // Return the response with user information and token
                return Response.ok(responseData).build();
            } else {
                // Authentication failed
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}