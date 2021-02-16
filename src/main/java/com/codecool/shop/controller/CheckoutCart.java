package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.log.Log;
import com.codecool.shop.model.log.LogItem;
import com.codecool.shop.model.log.LogItemFactory;
import com.codecool.shop.model.log.LogName;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/order"})
public class CheckoutCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Order order = new Order(cart);
        session.setAttribute("order", order);
        session.setAttribute("log", LogItemFactory.create(LogName.CREATE,order));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("totalPrice", cart.getTotalPrice());
        context.setVariable("cart", session.getAttribute("cart"));
        engine.process("order/order.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");

        String country = req.getParameter("country");
        String street = req.getParameter("street");
        String postCode = req.getParameter("postCode");
        String city = req.getParameter("city");

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(country);
        System.out.println(street);
        System.out.println(postCode);
        System.out.println(city);

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Order order = (Order) session.getAttribute("order");
        order.setUser(firstName);
        Log log = (Log) session.getAttribute("log");
        log.add(LogItemFactory.create(LogName.ADD_ADDRESS,order));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        context.setVariable("cart", session.getAttribute("cart"));

        engine.process("payment/payment.html", context, resp.getWriter());
    }
}

