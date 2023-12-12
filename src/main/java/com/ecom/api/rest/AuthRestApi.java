package com.ecom.api.rest;



import com.ecom.app.bean.AuthBeanI;
import com.ecom.app.model.entity.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthRestApi {

    @Inject
    private AuthBeanI authBean;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User loginUser) {
        try {
            User authenticatedUser = authBean.authenticate(loginUser);

            if (authenticatedUser != null) {
                return Response.ok(authenticatedUser).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
