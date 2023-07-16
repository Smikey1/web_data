package com.system.sastohub.pojo;

import com.system.sastohub.entity.MyCart;
import com.system.sastohub.entity.Product;
import com.system.sastohub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartPojo {
    private Integer cart_id;
    private Integer p_id;
    private Integer user_id;


    public AddToCartPojo(MyCart myCart){
        this.cart_id= myCart.getCartId();
        this.p_id= myCart.getUser().getId();
        this.user_id= myCart.getProduct().getProductId();

    }

}
