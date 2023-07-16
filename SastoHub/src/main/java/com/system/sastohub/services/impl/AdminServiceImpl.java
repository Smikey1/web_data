package com.system.sastohub.services.impl;

import com.system.sastohub.entity.Admin;
import com.system.sastohub.exception.AppException;
import com.system.sastohub.pojo.AdminPojo;
import com.system.sastohub.pojo.UserPojo;
import com.system.sastohub.repo.AdminRepo;
import com.system.sastohub.repo.UserRepo;
import com.system.sastohub.entity.User;
import com.system.sastohub.services.AdminServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminServices {
    private final AdminRepo adminRepo;

    @Override
    public String save(AdminPojo adminPojo){
        Admin admin =new Admin();
        admin.setFull_name(adminPojo.getFullname());
        admin.setAddress(adminPojo.getAddress());
        admin.setEmail(adminPojo.getEmail());
        admin.setMobileNo(adminPojo.getMobile_no());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(adminPojo.getPassword());
        admin.setPassword(encodePassword);

        adminRepo.save(admin);
        return "created";
    }

    @Override
    public AdminPojo findByEmail(String email) {
        Admin admin = adminRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new AdminPojo(admin);
    }


}
