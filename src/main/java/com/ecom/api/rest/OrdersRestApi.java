package com.ecom.api.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.model.entity.Order;

@Path("/orders")
public class OrdersRestApi extends BaseRestApi {

    @EJB
    private OrderBeanI orderBean;

    @RolesAllowed("LOGGED_IN")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Order order) {
        orderBean.addOrUpdate(order);
        return respond();
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(orderBean.list(Order.class));
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        orderBean.delete(Order.class, id);
        return respond();
    }

}
