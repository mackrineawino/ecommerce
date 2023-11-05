package com.ecom.app.model.view.css;

import java.io.Serializable;

public class AppCss implements Serializable {

    private String style = "<style>" +

            ".topnav {" +
            "overflow: hidden;" +
            "}" +

            ".topnav a {" +
            "margin: 43px 30px;" +
            "float: left;" +
            "color: #49A3C8;" +
            "text-align: center;" +
            "text-decoration: none;" +
            "font-size: 20px;" +
            "}" +

            ".topnav a:hover {" +
            "color: #E0588E;" +
            "}" +

             ".topnav a.active {" +
             "color: #E0588E;" +
             "}" +

            "</style>";

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
