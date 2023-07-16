package com.system.sastohub;

import com.system.sastohub.entity.User;
import com.system.sastohub.repo.UserRepo  ;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepo UserRepo;

    @Test
    @Order(1)
    public void saveUserTest() {

//        User User = new User();
//        User.setFullname("rak");

        User user = User.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
                .password("1234")
                .build();


        UserRepo.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public void getUserTest() {

        User user = User.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
                .password("1234")
                .build();


        UserRepo.save(user);


        User userCreated = UserRepo.findById(user.getId()).get();
        Assertions.assertThat(userCreated.getId()).isEqualTo(user.getId());

    }

    @Test
    @Order(3)
    public void getListOfUserTest(){
        User user = User.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
                .password("1234")
                .build();


        UserRepo.save(user);
        List<User> User = UserRepo .findAll();
        Assertions.assertThat(User.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    public void updateUserTest(){

        User user = User.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
                .password("1234")
                .build();


        UserRepo.save(user);

        User user1  = UserRepo.findById(user.getId()).get();

        user1.setFull_name("new name");

        User userUpdated  = UserRepo.save(user);

        Assertions.assertThat(userUpdated.getFull_name()).isEqualTo("new name");

    }

    @Test
    @Order(5)
    public void deleteUserTest(){

        User user = User.builder()
                .full_name("rak")
                .email("a@g.com")
                .mobileNo("98888888")
                .password("1234")
                .build();


        UserRepo.save(user);

//        @Query(value = "SELECT * from")

        User user1 = UserRepo.findById(user.getId()).get();
//        User user1 = UserRepo  .findById(user.getId()).get();
        UserRepo.delete(user1);

        User user2 = null;
        Optional<User> optionalUser = UserRepo.findByEmail("a@g.com");
        if(optionalUser.isPresent()){
            user2 = optionalUser.get();
        }

        Assertions.assertThat(user2).isNull();
//        Assertions.assertThat(User1.getId()).isNull();
    }
}

