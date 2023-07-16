package com.system.sastohub.repo;

import com.system.sastohub.entity.MyCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddToCartRepo extends JpaRepository<MyCart,Integer> {
    @Query(value = "SELECT * FROM mycart WHERE user_id = ?1", nativeQuery = true)
    Optional<List<MyCart>> fetchAll(Integer userId);

}
