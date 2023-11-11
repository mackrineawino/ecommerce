package com.ecom.app.model.view.html;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpPage {
  public void renderSignup(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String jspPage = "register.jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(jspPage);
        try {
          dispatcher.forward(req, resp);
        } catch (ServletException e) {
          e.printStackTrace();
        }
  }
}