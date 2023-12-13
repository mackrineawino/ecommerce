package com.ecom.api.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.model.entity.Product;

@Path("/api/products")
public class ProductsRestApi extends BaseRestApi {

    @EJB
    private ProductBeanI productBean;


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Product product) {
        productBean.addOrUpdate(product);
        return respond();
    }

    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(productBean.list(Product.class));
    }

  
    @Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        System.out.println("id " + id);
        Map<String, Object> filters = new HashMap<>();
        filters.put("id", id);
        return respond(productBean.list(Product.class, filters));

    }

   
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        productBean.delete(Product.class, id);
        return respond();
    }
}
