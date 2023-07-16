package com.system.sastohub.controller;

import com.system.sastohub.entity.Product;
import com.system.sastohub.pojo.AddToCartPojo;
import com.system.sastohub.services.AddToCartService;
import com.system.sastohub.services.ProductService;
import com.system.sastohub.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomepageController {
    private final ProductService productService;
    private final UserServices userService;
    private final AddToCartService productCartService;



//    @GetMapping("/homepage")
//    public String homePage() {
//        return "mainhomepage";
//    }

    @GetMapping("/homepage")
    public String getAllProduct(Model model, Principal principal, Authentication authentication){
        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/Admin-dashboard";
                }
            }
        }

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/user/login";
        }
        List<Product> hproduct = productService.fetchAll();
        if (principal!=null) {
            model.addAttribute("userdata", userService.findByEmail(principal.getName()));
        }
//        List<Product> hproduct = productService.fetchAll();

////        model.addAttribute("products", hproduct.stream().map(product ->
//                Product.builder()
//                        .productId(product.getProductId())
//                        .productTitle(product.getProductTitle())
//                        .imageBase64(getImageBase64(product.getImage()))
//                        .build()
//        ));
        model.addAttribute("product", hproduct);
//        model.addAttribute("savecarts", new AddToCartPojo());
        return "mainhomepage";
    }



    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/sastohubimages/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }


}