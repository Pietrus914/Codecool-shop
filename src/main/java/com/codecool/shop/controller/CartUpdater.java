package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/cart/update"})
public class CartUpdater extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        String action = req.getParameter("action");
//        System.out.println("action: " +action);
//        System.out.println("Product: " +req.getParameter("name"));
        if (action.equals("remove")){
            cart.remove(Integer.parseInt(req.getParameter("id")));
        } else if (action.equals("decrease")){
            cart.decreaseQuantity(Integer.parseInt(req.getParameter("id")));
        } else if (action.equals("increase")){
            cart.increaseQuantity(Integer.parseInt(req.getParameter("id")), 1);
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("/cart");
    }
}
