package com.system.sastohub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    @GetMapping("/payment")
    public String pay() {
        return "Payment";
    }


}
