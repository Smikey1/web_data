package com.system.sastohub.services;

import com.system.sastohub.pojo.AdminPojo;

public interface AdminServices {
    String save (AdminPojo adminPojo);
    AdminPojo findByEmail(String email);
}
