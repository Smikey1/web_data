package com.system.sastohub.pojo;


import com.system.sastohub.entity.Admin;
import com.system.sastohub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminPojo {
    private Integer id;
    private String email;
    private String address;
    private String mobile_no;
    private String fullname;
    private String password;

    public AdminPojo(Admin admin){
        this.id=admin.getId();
        this.email=admin.getEmail();
        this.fullname=admin.getFull_name();
        this.mobile_no=admin.getMobileNo();
        this.password=admin.getPassword();
        this.address= admin.getAddress();

    }

}
