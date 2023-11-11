package com.ecom.app.model.view.html;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlFormAnnotations {
    String label() default "Form";
    String url() default ".";
    String httpMethod() default "POST";

}
