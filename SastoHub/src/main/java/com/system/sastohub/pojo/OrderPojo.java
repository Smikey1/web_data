package com.system.sastohub.pojo;

import com.system.sastohub.entity.Order;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPojo {

    private Integer orderId;
    private Date orderDate;

    private boolean isDelivered;

    private Integer pid;

    private Integer id;

    private Integer quantity;
    private MultipartFile image;



    public OrderPojo( Order order) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.isDelivered = order.isOrderDelivered();
        this.pid = order.getProduct().getProductId();
        this.id = order.getUser().getId();
        this.quantity = order.getQuantity();

    }
}
