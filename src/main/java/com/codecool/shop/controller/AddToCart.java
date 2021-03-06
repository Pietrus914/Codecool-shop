package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.ProductLine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add/to/cart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductLine productLine = new ProductLine(productDataStore.find(Integer.parseInt(req.getParameter("id"))));

        if (cart.getProductLines().get(productLine.getProduct().getId()) != null) {
            cart.increaseQuantity(productLine.getProduct().getId(), Integer.parseInt(req.getParameter("value")));
        } else {
            cart.add(new ProductLine(productDataStore.find(Integer.parseInt(req.getParameter("id")))));
            cart.increaseQuantity(productLine.getProduct().getId(), Integer.parseInt(req.getParameter("value"))-1);
        }

        resp.sendRedirect("/");
    }
}
