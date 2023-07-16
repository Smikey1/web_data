package com.system.sastohub.services;

import com.system.sastohub.entity.MyCart;
import com.system.sastohub.pojo.AddToCartPojo;

import java.util.List;

public interface AddToCartService {

    List<MyCart> fetchAll(Integer id);



    AddToCartPojo saveToCart(AddToCartPojo addToCartPojo);

    MyCart fetchById(Integer id);

    String updateQuantity(MyCart productCart);

    void deleteFromCart(Integer id);
    MyCart fetchOne(Integer id);

    AddToCartPojo save(AddToCartPojo productCartPojo);
}
