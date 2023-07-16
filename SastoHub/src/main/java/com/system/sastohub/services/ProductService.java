package com.system.sastohub.services;

import com.system.sastohub.entity.Product;
import com.system.sastohub.pojo.ProductPojo;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductPojo saveProduct(ProductPojo productpojo) throws IOException;

    Product fetchById(Integer id);

    List <Product> fetchAll();

    void deleteById(Integer id);



}
