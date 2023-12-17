package com.ecom.api.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Order;

@Path("/orders")
public class OrdersRestApi extends BaseRestApi {

    @EJB
    private OrderBeanI orderBean;

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

     @GET
    @Path("/{orderId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemCart> getOrderItems(@PathParam("orderId") Long orderId) {
        // Fetch order items based on the order ID
        Order order = orderBean.findOrderById(orderId);
        if (order != null) {
            return order.getOrderItems();
        } else {
            // Return an empty list or handle the case where the order is not found
            return List.of();
        }
    }

}
