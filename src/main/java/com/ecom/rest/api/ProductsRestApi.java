package com.ecom.rest.api;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.model.entity.Product;

@Path("/products")
public class ProductsRestApi  extends BaseRestApi{

    @EJB
    private ProductBeanI productBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Product product){
        productBean.addOrUpdate(product);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(productBean.list(Product.class));
    }
}
