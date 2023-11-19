package com.ecom.app.model.view.html;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

public class HtmlForm {
    public static String form(Class<?> model) {

        String htmlForm = "<h3 style=\"text-align: center;\">ADD ITEMS</h3>\n" +
                "<form action=\"./addProduct\" method=\"post\">\n";

        Field[] fields = model.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlFormFieldAnnotation.class)) {
                continue;
            }
            HtmlFormFieldAnnotation formFieldAnnotate = field.getAnnotation(HtmlFormFieldAnnotation.class);
            String fieldName = field.getName();
            String inputId = (StringUtils.isBlank(formFieldAnnotate.id()) ? fieldName : formFieldAnnotate.id());
            boolean isEnum = field.getType().isEnum();
    
            htmlForm += "<label for=\"" + inputId + "\">" + (StringUtils.isBlank(formFieldAnnotate.label()) ? fieldName : formFieldAnnotate.label()) + ":</label><br>\n";
        
            if (isEnum) {
                htmlForm += "<select name=\"" + (StringUtils.isBlank(formFieldAnnotate.name()) ? fieldName : formFieldAnnotate.name()) + "\" id=\"" + fieldName + "\" required>";
            
                for (Object category : field.getType().getEnumConstants()) {
                    htmlForm += "<option value=\"" + category + "\">" + category + "</option>";
                }
                htmlForm += "</select><br>";
            } else {
                htmlForm += "<input type=\"text\" id=\"" + inputId + "\" name=\"" + (StringUtils.isBlank(formFieldAnnotate.name()) ? fieldName : formFieldAnnotate.name()) + "\" style=\" width: 80%;\" required><br><br>\n";
            }
            
        }
        
        htmlForm += "<input style=\"background: #49A3C8; display: flex; height: 35px; width: 80%; text-align: center;\" type=\"submit\" value=\"Submit\">\n";

        htmlForm += "</form>\n";

        return htmlForm.toString();
    }
}
