package com.system.sastohub.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")

public class Product {
    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer productId;

    private String productTitle;

    private String productCategory;

    private String brandName;

    private String size;

    private String productDescription;

    private Double productPrice;

    private String image;

    @Transient
    private String imageBase64;

}
