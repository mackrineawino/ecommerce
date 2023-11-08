package com.ecom.app.model.view.html;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.app.model.entity.Product;

public class AddProductPage {
    public void renderAddProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter print = resp.getWriter();
        print.write("<!DOCTYPE html>\n" + //
                "<html>\n" + //
                "<head>\n" + //
                "    <title>Product Entry Form</title>\n" + //
                "    <style>\n" + //
                " body{\n" + //
                "margin:0;\n" + //
                "padding:0;\n" +
                " }\n" +
                "       input {\n" + //
                "            margin-right: 50px;\n" + //
                "            width: 90%;\n" +
                "            height: 30px;\n" +
                "            border-radius: 5px;\n" +
                "            background-color: transparent;\n" +
                "        }\n" + //
                "     li{\n" +
                "         margin-top: 40px;\n" +
                "         text-align: left;\n" +
                " }\n" +
                "    </style>\n" + //
                "</head>\n" + //
                "<body>\n" + //
                "<div style=\" display: flex; height: 100vh;\">" +
                "<div style=\"display: flex; flex-direction: column; height: 100vh; width: 300px; background: #1A1D16;  padding: 1.7rem 0rem; text-align: center; \">"
                +
                "<h2 style=\"margin: 0px 20px; text-decoration: none; font-size: 30px; cursor: pointer;\"><span style=\"color: #E0588E;\"><span\n"
                + //
                "                        style=\"font-size: 50px\">C</span>ool</span></span><span style=\"color: #49A3C8;\"><span\n"
                + //
                "                    style=\"font-size: 50px\">S</span>tuff</span><h2> " +
                "<h2 style=\"color: white;\">ADMIN DASHBOARD</h2>" +
                "<ul style=\" display: block; list-style-type: none;  \" class=\"navbar\">\n" + //
                "        <li class=\"navitem\"><a href=\"./admin\" style=\"color: white; text-decoration: none; padding: 14px 16px;\">ADD ITEMS</a></li>\n"
                + //
                "        <li class=\"navitem\"><a href=\"./delete\" style=\" color: white; text-decoration: none;   padding: 14px 16px;\">DELETE ITEMS</a></li>\n"
                + //
                "        <li class=\"navitem\"><a href=\"#\" style=\" color: white; text-decoration: none; padding: 14px 16px;\">UPDATE ITEMS</a></li>\n"
                + // padding: 14px 16px;
                "        <li class=\"navitem\"><a href=\"#\" style=\" color: white; text-decoration: none; padding: 14px 16px;\">VIEW ITEMS</a></li>\n"
                + //
                "    </ul>" +
                "<a href=\"./logout\" style=\" text-align: left; margin-left: 40px; text-decoration: none; color: white; padding: 14px 16px;\">LOGOUT</a>"
                +
                "</div>" +
                "<div style=\"padding-left: 50px; width:100%; background: #EFF7FB;\">");
        print.write(HtmlForm.form(Product.class));
        print.write("</div>" +
                "</div>" +
                "</body>\n" + //
                "</html>\n" + //
                "");
    }
}
