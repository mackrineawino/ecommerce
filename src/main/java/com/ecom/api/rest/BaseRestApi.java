package com.ecom.api.rest;

import javax.ws.rs.core.Response;

public abstract class BaseRestApi {

    Response respond(){
        return Response.status(Response.Status.OK).entity(new RestResponceWrapper()).build();
    }

    Response respond(Object object){
        return Response.status(Response.Status.OK).entity(object).build();
    }

    Response respond(RestResponceWrapper wrapper){
        return Response.status(wrapper.isSuccess()?Response.Status.OK:Response.Status.INTERNAL_SERVER_ERROR).entity(wrapper).build();
    }
}
