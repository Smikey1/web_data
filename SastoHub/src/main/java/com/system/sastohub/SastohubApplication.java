package com.system.sastohub;

//import com.system.sastohub.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SastohubApplication {

    @Autowired
//    private EmailSenderService senderService;
    public static void main(String[] args) {
        SpringApplication.run(SastohubApplication.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendEmail(){
//        senderService.sendSimpleEmail("aryalaashutosh@gmail.com",
//                "this is Subject",
//                "this is body part");
//
//    }

}
