package com.system.sastohub.services.impl;

import com.system.sastohub.entity.Order;
import com.system.sastohub.pojo.OrderPojo;
import com.system.sastohub.repo.OrderRepo;
import com.system.sastohub.repo.ProductRepo;
import com.system.sastohub.repo.UserRepo;
import com.system.sastohub.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    private final UserRepo userRepo;

    private final ProductRepo productRepo;

    @Override
    public OrderPojo saveOrder(OrderPojo orderPojo) throws IOException {
        Order order = new Order();

        order.setOrderId(orderPojo.getOrderId());
        order.setOrderDate(orderPojo.getOrderDate());
        order.setProduct(productRepo.findById(orderPojo.getPid()).orElseThrow());
        order.setUser(userRepo.findById(orderPojo.getId()).orElseThrow());
        order.setQuantity(orderPojo.getQuantity());

//        if(orderPojo.getImage()!=null){
//            StringBuilder fileNames = new StringBuilder();
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, orderPojo.getImage().getOriginalFilename());
//            fileNames.append(orderPojo.getImage().getOriginalFilename());
//            Files.write(fileNameAndPath, orderPojo.getImage().getBytes());
//
//            order.setImage(orderPojo.getImage().getOriginalFilename());
//        }

        orderRepo.save(order);
        return new OrderPojo(order);
    }

    @Override
    public List<Order> fetchAll() {
//        System.out.println( findAllInOrder(orderRepo.findAll()));
        return orderRepo.findAll();
    }

    public List<Order> findAllInOrder(List<Order> list){
        Stream<Order> allOrders=list.stream().map(order ->
                Order.builder()
                        .orderId(order.getOrderId())
                        .product(order.getProduct())
                        .user(order.getUser())
                        .orderDate(order.getOrderDate())
                        .orderDelivered(order.isOrderDelivered())
                        .quantity(order.getQuantity())
                        .build()
                );

                list = allOrders.toList();
        return list;
    }

    @Override
    public Order fetchById(Integer id) {
        Order application= orderRepo.findById(id).orElseThrow(()->new RuntimeException("not found application"));
        application =Order.builder()
                .orderId(application.getOrderId())
                .user(application.getUser())
                .product(application.getProduct())
                .build();
        return application;

    }
    @Override
    public List<Order> findBookingById(Integer id) {
        return findAllInOrder(orderRepo.findBookingById(id));

    }

}
