package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/cart/remove"})
public class RemoverFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        String action = req.getParameter("action");
        if (action.equals("remove")){
            cart.remove(req.getParameter("name"));
        } else if (action.equals("decrease")){
            cart.decreaseQuantity(req.getParameter("name"));
        }



//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("productsLines", cart.getProductLines());
//        context.setVariable("cart", cart);
//
//        engine.process("cart/cartView.html", context, resp.getWriter());

        resp.sendRedirect("/cart");
    }
}
