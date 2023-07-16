package com.system.sastohub.services;

import com.system.sastohub.entity.Order;
import com.system.sastohub.pojo.OrderPojo;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    OrderPojo saveOrder(OrderPojo orderPojo) throws IOException;

    List<Order> fetchAll();

    Order fetchById(Integer id);


    List<Order> findBookingById(Integer id);
}
