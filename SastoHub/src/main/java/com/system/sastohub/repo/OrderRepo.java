package com.system.sastohub.repo;

import com.system.sastohub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository <Order , Integer> {
    @Query(value = "SELECT * FROM orders where user_id=?1", nativeQuery = true)
    List<Order> findBookingById(Integer id);

//    Optional<Order> findOrderByEmail(String email);
}
