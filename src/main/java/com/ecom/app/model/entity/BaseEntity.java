package com.ecom.app.model.entity;


import java.io.Serializable;

import com.ecom.app.model.view.html.DbTableColAnnotation;
import com.ecom.app.model.view.html.DbTableId;

public class BaseEntity implements Serializable {

    @DbTableId
    @DbTableColAnnotation(name = "id", definition = "int")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
