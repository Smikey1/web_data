package com.system.sastohub.repo;

import com.system.sastohub.entity.Admin;
import com.system.sastohub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    @Query(value="select * from admin where email=?1", nativeQuery = true)
    Optional<Admin> findByEmail(String email);
}
