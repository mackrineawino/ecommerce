package com.ecom.api.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.app.bean.CartBeanI;
import com.ecom.app.model.entity.ItemCart;

@Path("/cartItems")
public class CartRestApi extends BaseRestApi {

    @EJB
    private CartBeanI cartBean;

    @RolesAllowed("LOGGED_IN")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ItemCart itemCart) {
        cartBean.addOrUpdate(itemCart);
        return respond();
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/addMore/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMore(@PathParam("id") Long id) {
        cartBean.addMoreQuantity(id);
        return respond();
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/decreaseQuantity/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response reduceQuantity(@PathParam("id") Long id) {
        cartBean.reduceQuantity(id);
        return respond();
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(cartBean.list(ItemCart.class));
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        cartBean.delete(ItemCart.class, id);
        return respond();
    }
}
