package com.system.sastohub.pojo;

import com.system.sastohub.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPojo {
    private Integer pid;

    private String ptitle;

    private String pCategories;

    private String pDesc;

    private String bName;

    private String size;

    private double pPrice;

    private MultipartFile image;

    public ProductPojo(Product product){
        this.pid=product.getProductId();
        this.ptitle=product.getProductTitle();
        this.pCategories= product.getProductCategory();
        this.pDesc = product.getProductDescription();
        this.pPrice = product.getProductPrice();
        this.bName = product.getBrandName();
        this.size = product.getSize();
    }

}
