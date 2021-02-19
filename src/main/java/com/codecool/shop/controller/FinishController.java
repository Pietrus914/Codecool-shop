package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.LogDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.LogDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.PaymentProviderDao;
import com.codecool.shop.dao.implementation.PaymentProviderDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.log.Log;
import com.codecool.shop.model.log.LogItem;
import com.codecool.shop.model.log.LogItemFactory;
import com.codecool.shop.model.log.LogName;
import com.codecool.shop.util.SendEmail;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//        Boolean status = false;

        SendEmail sendEmail = new SendEmail();
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        if (status){
            sendEmail.sendEmail(order);
            log.add(LogItemFactory.create(LogName.FINISHED, order));
            LogDaoMem.getInstance().save(log, data);
            log.clear();
            req.getSession().removeAttribute("log");

            order.getPayment().setStatus(1);
            OrderDaoMem.getInstance().save(order, data);
            cart.clear();
            req.getSession().removeAttribute("order");

        } else {
            LogItem item = LogItemFactory.create(LogName.ERROR, order);
            item.setDescription("payment failed");
            log.add(item);
            LogDaoMem.getInstance().save(log, data);
            order.getPayment().setStatus(0);
            OrderDaoMem.getInstance().save(order, data);
        }

        context.setVariable("cart", cart);
        context.setVariable("successful", status);

        engine.process("finish/finish.html", context, resp.getWriter());

    }
}
