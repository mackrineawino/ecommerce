package com.ecom.app.model.view.html;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
                "            margin-right: 50px; /* Adjust as needed for spacing */\n" + //
                "            width: 90%;\n" +
                "            height: 30px;\n"  +
                "            border-radius: 5px;\n" +
                "background-color: transparent;\n" +
                "        }\n" + //
                "     li{\n" +
                "         margin-top: 40px;\n" + //
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
                + //padding: 14px 16px;
                "        <li class=\"navitem\"><a href=\"#\" style=\" color: white; text-decoration: none; padding: 14px 16px;\">VIEW ITEMS</a></li>\n"
                + //
                "    </ul>" +
                "<a href=\"./logout\" style=\" text-align: left; margin-left: 40px; text-decoration: none; color: white; padding: 14px 16px;\">LOGOUT</a>" +
                "</div>" +
                "<div style=\"padding-left: 50px; width:100%; background: #EFF7FB;\">" +
                "    <h3 style=\"text-align: center;\">ADD ITEMS</h3>\n" + //
                "    <form action=\"./admin\" method=\"post\">\n" + //
                "        <label for=\"id\">Product ID:</label>\n" + //
                "        <input type=\"text\" id=\"id\" name=\"id\" required><br><br>\n" + //
                "\n" + //
                "        <label for=\"name\">Product Name:</label>\n" + //
                "        <input type=\"text\" id=\"name\" name=\"name\" required><br><br>\n" + //

                "        <label for=\"price\">Product Price:</label>\n" + //
                "        <input type=\"text\" id=\"price\" name=\"price\" required><br><br>\n" + //
                "\n" + //
                "        <label for=\"availability\">Availability:</label>\n" + //
                "        <input type=\"text\" id=\"availability\" name=\"availability\" required><br><br>\n" + //
                 "\n" + //
                "        <label for=\"imageUrl\">Image URL:</label>\n" + //
                "        <input type=\"text\" id=\"imageUrl\" name=\"imageUrl\" required><br><br>\n" + //
                "\n" + //
                "\n" + //
                "        <label for=\"description\">Description:</label><br>\n" + //
                "        <textarea id=\"description\" style=\"background-color: transparent; width: 60%; height: 50px; border-radius: 5px; \" name=\"description\" required></textarea>\n" + //
                "\n" + //
                "\n" + //
                "        <label for=\"category\">Product Category:</label>\n" + //
                "        <select id=\"category\" name=\"category\" required>\n" + //
                "            <option value=\"SNEAKER\">Sneaker</option>\n" + //
                "            <option value=\"BOOT\">Boot</option>\n" + //
                "            <option value=\"DOLL_SHOE\">Doll shoe</option>\n" + //
                "             <option value=\"STILETTO\">Stiletto</option>\n" + //
                "            <!-- Add more category options as needed -->\n" + //
                "        </select><br><br>\n" + //
                "        <input style=\"background: #49A3C8; height: 35px;\"type=\"submit\" value=\"Submit\">\n" + //
                "    </form>\n" + //
                "</div>" +
                "</div>" +
                "</body>\n" + //
                "</html>\n" + //
                "");
                        }
}
