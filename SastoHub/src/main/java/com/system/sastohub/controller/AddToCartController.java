package com.system.sastohub.controller;

import com.system.sastohub.entity.MyCart;
import com.system.sastohub.entity.Product;
import com.system.sastohub.pojo.AddToCartPojo;
import com.system.sastohub.repo.AddToCartRepo;
import com.system.sastohub.services.AddToCartService;
import com.system.sastohub.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.text.ParseException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class AddToCartController {

    private final AddToCartService productCartService;

    private final UserServices userService;

    @GetMapping()
    public String displayCart(Principal principal, Model model, AddToCartPojo productCartPojo){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<MyCart> list = productCartService.fetchAll(id);
        model.addAttribute("cart", productCartPojo);
        model.addAttribute("cartItems", list);

        return "mycart";
    }


    @PostMapping("/updateQuantity/{id}")
    public String updateQuantity(@Valid AddToCartPojo productCartPojo){
        MyCart productCart = productCartService.fetchOne(productCartPojo.getCart_id());
//        productCart.setQuantity(productCartPojo.getQuantity());
        productCartService.updateQuantity(productCart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String deleteCartItem(@PathVariable("id") Integer id){
        productCartService.deleteFromCart(id);
        return "redirect:/cart";
    }
}
