package com.ecom.app.model.view.html;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

public class HtmlForm {
    public static String form(Class<?> model) {

        String htmlForm = "\n" +
                "<form action=\"./addProduct\" method=\"post\">\n";

        Field[] fields = model.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlFormFieldAnnotation.class)) {
                continue;
            }
            HtmlFormFieldAnnotation formFieldAnnotate = field.getAnnotation(HtmlFormFieldAnnotation.class);
        
            String fieldName = field.getName();
            String inputId = (StringUtils.isBlank(formFieldAnnotate.id()) ? fieldName : formFieldAnnotate.id());
        
            htmlForm += "<label for=\"" + inputId + "\">" + (StringUtils.isBlank(formFieldAnnotate.label()) ? fieldName : formFieldAnnotate.label()) + ":</label><br>\n";
        
            if ("productDescription".equals(fieldName)) {
                htmlForm += "<textarea style=\"background-color: transparent; outline: black; width: 80%; height: 50px; border-radius: 5px;\" id=\"" + inputId + "\" name=\"" + fieldName + "\" required></textarea><br><br>\n";
            } else if ("category".equals(fieldName)) {
                htmlForm += "<select id=\"" + inputId + "\" name=\"" + fieldName + "\" required>\n" +
                    "<option value=\"SNEAKER\">Sneaker</option>\n" +
                    "<option value=\"BOOT\">Boot</option>\n" +
                    "<option value=\"DOLL_SHOE\">Doll shoe</option>\n" +
                    "<option value=\"STILETTO\">Stiletto</option>\n" +
                    "</select></br></br>";
            } else {
                htmlForm += "<input type=\"text\" id=\"" + inputId + "\" name=\"" + (StringUtils.isBlank(formFieldAnnotate.name()) ? fieldName : formFieldAnnotate.name()) + "\" style=\" width: 80%;\" required><br><br>\n";
            }
        }
        
        htmlForm += "<input style=\"background: #49A3C8; display: flex; height: 35px; width: 80%;\" type=\"submit\" value=\"Submit\">\n";
        htmlForm += "</form>\n";

        return htmlForm;
    }
}
