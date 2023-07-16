package com.system.sastohub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Builder
@Entity
@Getter
@Setter
@Table(name = "mycart")
@NoArgsConstructor
@AllArgsConstructor
public class MyCart {
    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FKM_User_Id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName = "productId",
            foreignKey = @ForeignKey(name = "FKM_Product_Id"))
    private Product product;



}