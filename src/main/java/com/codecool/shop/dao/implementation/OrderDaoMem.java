package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.util.JsonWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

   private List<Order> data = new ArrayList<>();
   private static OrderDaoMem instance = null;


   private OrderDaoMem(){}

    public static OrderDaoMem getInstance(){
        if (instance == null){
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Order order) {
        order.setId(data.size() + 1);
        data.add(order);
    }

    @Override
    public void save(Order order) {
        JsonWriter.saveToFile(order, "orders/order");
    }


}
