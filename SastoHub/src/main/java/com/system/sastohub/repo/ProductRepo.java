package com.system.sastohub.repo;

import com.system.sastohub.entity.Order;
import com.system.sastohub.entity.Product;
import com.system.sastohub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository < Product, Integer>{
//    Optional<Product> findProductByName(String name);
}


