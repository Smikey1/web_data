package com.system.sastohub.pojo;


import com.system.sastohub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private Integer id;
    private String email;
    private String address;
    private String mobile_no;
    private String fullname;
    private String password;

    public UserPojo(User user){
        this.id=user.getId();
        this.email=user.getEmail();
        this.fullname=user.getFull_name();
        this.mobile_no=user.getMobileNo();
        this.password=user.getPassword();
        this.address= user.getAddress();

    }

}
