package com.ecom.actions.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.actions.BaseAction;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.ProductBeanImpl;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.AddProductPage;

@WebServlet("/addProduct")
public class ProductAction extends BaseAction {

    private ProductBeanI productBean = new ProductBeanImpl();
    private Product product=new Product();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        new AddProductPage().renderAddProducts(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serializeForm(product, req.getParameterMap());
        productBean.addOrUpdateProduct(product);

        resp.sendRedirect("./addProduct");
    }

}
