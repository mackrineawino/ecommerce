package com.ecom.actions;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.model.entity.MpesaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/checkout")
public class CheckoutAction extends BaseAction {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader reader = req.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            MpesaResponse mpesaResponse = objectMapper.readValue(json.toString(), MpesaResponse.class);

            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": false, \"error\": \"Internal server error\"}");
        }
    }
}
