package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.PaymentProviderDao;
import com.codecool.shop.dao.implementation.PaymentProviderDaoMem;
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
import java.util.Random;

@WebServlet(urlPatterns = {"/finish"})
public class FinishController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        Order order = (Order) req.getSession().getAttribute("order") ;
        Log log = (Log) req.getSession().getAttribute("log") ;

        Random random = new Random();
        Boolean status = random.nextBoolean();


        if (status){
            log.add(LogItemFactory.create(LogName.FINISHED, order));
            cart.clear();
            req.getSession().removeAttribute("order");

        } else {
            LogItem item = LogItemFactory.create(LogName.ERROR, order);
            item.setDescription("payment failed");
            log.add(item);
        }

        context.setVariable("cart", cart);
        context.setVariable("successful", status);

        engine.process("finish/finish.html", context, resp.getWriter());

    }
}
