package com.ecom.rest.api;
import java.io.Serializable;

public class RestResponceWrapper implements Serializable {

    private boolean success;

    private String message;

    public RestResponceWrapper(){
        this.success = true;
        this.message = "OK";
    }

    public RestResponceWrapper(String message){
        this.success = true;
        this.message = message;
    }

    public RestResponceWrapper(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}