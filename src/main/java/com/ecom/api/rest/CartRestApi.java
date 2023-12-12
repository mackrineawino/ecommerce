package com.ecom.api.rest;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.app.bean.CartBeanI;
import com.ecom.app.model.entity.ItemCart;



@Path("/cartItems")
public class CartRestApi  extends BaseRestApi{

    @EJB
    private CartBeanI cartBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ItemCart itemCart){
        cartBean.addOrUpdate(itemCart);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return respond(cartBean.list(ItemCart.class));
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        cartBean.delete(ItemCart.class, id);
        return respond();
    }

}
