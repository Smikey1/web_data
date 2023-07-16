package com.system.sastohub;

import com.system.sastohub.entity.Product;
import com.system.sastohub.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepo ProductRepo;

    @Test
    @Order(1)
    public void saveUserTest() {

//        Product product = new User();
//        User.setFullname("rak");

        Product product = Product.builder()
                .brandName("bhattu")
                .productTitle("Jeans")
                .build();


        ProductRepo.save(product);

        Assertions.assertThat(product.getProductId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public void getUserTest() {

        Product product = Product.builder()
                .brandName("bhattu")
                .productTitle("Jeans")
                .build();


        ProductRepo.save(product);


        Product productCreated = ProductRepo.findById(product.getProductId()).get();
        Assertions.assertThat(productCreated.getProductId()).isEqualTo(product.getProductId());

    }

    @Test
    @Order(3)
    public void getListOfUserTest(){
        Product product = Product.builder()
                .brandName("bhattu")
                .productTitle("Jeans")
                .build();


        ProductRepo.save(product);
        List<Product> products = ProductRepo .findAll();
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }


//    @Test
//    @Order(4)
//    public void updateUserTest(){
//
//        Product product = Product.builder()
//                .brandName("bhattu")
//                .productTitle("Jeans")
//                .build();
//
//
//        ProductRepo.save(product);
//
//        Product product1  = ProductRepo.findById(product.getProductId()).get();
//
//        product1.setProductTitle("new Title");
//
//        Product productUpdated  = ProductRepo.save(product);
//
//        Assertions.assertThat(productUpdated.setProductTitle()).isEqualTo("new Title");
//
//    }

    @Test
    @Order(5)
    public void deleteUserTest(){

        Product product = Product.builder()
                .brandName("bhattu")
                .productTitle("Jeans")
                .build();


        ProductRepo.save(product);

//        @Query(value = "SELECT * from")

        Product product1 = ProductRepo.findById(product.getProductId()).get();
//        Product product1 = ProductRepo  .findById(product.getProductId()).get();
        ProductRepo.delete(product1);

        Product product2 = null;
        Optional<Product> optionalProduct = ProductRepo.findProductByName("a@g.com");
        if(optionalProduct.isPresent()){
            product2 = optionalProduct.get();
        }

        Assertions.assertThat(product2).isNull();
//        Assertions.assertThat(User1.getId()).isNull();
    }
}

