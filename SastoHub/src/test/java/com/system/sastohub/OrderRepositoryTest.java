package com.system.sastohub;

import com.system.sastohub.entity.User;
import com.system.sastohub.repo.OrderRepo  ;
import org.aspectj.weaver.ast.Or;
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
public class OrderRepositoryTest {

    @Autowired
    private OrderRepo OrderRepo;

    @Test
    @Order(1)
    public void saveUserTest() {

//                com.system.sastohub.entity.Order order = new User();
//        User.setFullname("rak");

                com.system.sastohub.entity.Order order = com.system.sastohub.entity.Order.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
//                .password("1234")
                .build();


        OrderRepo.save(order);

        Assertions.assertThat(order.getOrderId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public void getUserTest() {

                com.system.sastohub.entity.Order order = com.system.sastohub.entity.Order.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
//                .password("1234")
                .build();


        OrderRepo.save(order);


                com.system.sastohub.entity.Order orderCreated = OrderRepo.findById(order.getOrderId()).get();
        Assertions.assertThat(orderCreated.getOrderId()).isEqualTo(order.getOrderId());

    }

//    @Test
//    @Order(3)
//    public void getListOfUserTest(){
//                com.system.sastohub.entity.Order order = com.system.sastohub.entity.Order.builder()
//                .full_name("rak")
//                .email("a@g.com")
//                .mobileNo("98888888")
////                .password("1234")
//                .build();
//
//
//        OrderRepo.save(order);
//        List<com.system.sastohub.entity.Order> Order = OrderRepo .findAll();
//        Assertions.assertThat(com.system.sastohub.entity.Order.size()).isGreaterThan(0);
//    }


    @Test
    @Order(4)
    public void updateUserTest(){

                com.system.sastohub.entity.Order order = com.system.sastohub.entity.Order.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
//                .password("1234")
                .build();


        OrderRepo.save(order);

                com.system.sastohub.entity.Order order1  = OrderRepo.findById(order.getOrderId()).get();

        order1.setFull_name("new name");

                com.system.sastohub.entity.Order orderUpdated  = OrderRepo.save(order);

        Assertions.assertThat(orderUpdated.getFull_name()).isEqualTo("new name");

    }

    @Test
    @Order(5)
    public void deleteUserTest(){

        com.system.sastohub.entity.Order order = com.system.sastohub.entity.Order.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
//                .password("1234")
                .build();


        OrderRepo.save(order);

//        @Query(value = "SELECT * from")

                com.system.sastohub.entity.Order order1 = OrderRepo.findById(order.getOrderId()).get();
//                com.system.sastohub.entity.Order order1 = OrderRepo  .findById(getOrderId()).get();
        OrderRepo.delete(order1);

                com.system.sastohub.entity.Order order2 = null;
            Optional<com.system.sastohub.entity.Order> optionalUser = OrderRepo.findOrderByEmail("a@g.com");
        if(optionalUser.isPresent()){
            order2 = optionalUser.get();
        }

        Assertions.assertThat(order2).isNull();
//        Assertions.assertThat(User1.getId()).isNull();
    }
}

 