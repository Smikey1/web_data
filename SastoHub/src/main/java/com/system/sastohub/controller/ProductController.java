package com.system.sastohub.controller;

import com.system.sastohub.entity.Product;
import com.system.sastohub.pojo.AddToCartPojo;
import com.system.sastohub.pojo.OrderPojo;
import com.system.sastohub.pojo.ProductPojo;
import com.system.sastohub.services.AddToCartService;
import com.system.sastohub.services.ProductService;

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
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final UserServices userService;
    private final AddToCartService productCartService;

    @GetMapping("/addProduct")
    public String createProduct(Model model){
        model.addAttribute("product" , new ProductPojo());
        return "/adminproduct";

    }

    @PostMapping("/save")
    public String saveProduct(@Valid ProductPojo productPojo,  BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/product/addProduct";
        }

        productService.saveProduct(productPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");


        return "redirect:/product/list";
    }

    @PostMapping("/saveP")
    public String savecart(@Valid AddToCartPojo productCartPojo) {
        productCartService.save(productCartPojo);
        return "redirect:/cart";
    }

    @GetMapping("/{id}")
    public String fetchById(@PathVariable Integer id, Model model,Principal principal){
        List<Product> hproduct = productService.fetchAll();
        Product product= productService.fetchById(id);
        model.addAttribute("product", hproduct);
        model.addAttribute("savecarts", new AddToCartPojo());
        model.addAttribute("product", new ProductPojo(product));
        model.addAttribute("pro", product);
        model.addAttribute("saveorder",new OrderPojo());
        model.addAttribute("loggedUser",userService.findByEmail(principal.getName()));
        return "browseproduct";
    }

//    @GetMapping("/{id}")
//    public String fetchById(@PathVariable Integer id, Model model, Principal principal){
//        Product product= productService.fetchById(id);
////        User user = userService.findById(id);
//        model.addAttribute("product", product);
//        model.addAttribute("getUser", userService.findByEmail(principal.getName()));
////        model.addAttribute("getUser", new UserPojo(user));
//        return "browseproduct";
//    }

//    @GetMapping("/addtocart")
//    public String addTocart(@PathVariable Integer id, Model model){
//        Product product= productService.fetchById(id);
//        model.addAttribute("product", product);
//        return "index";
//    }

    @GetMapping("/list")
    public String productList(Model model){
        List <Product> products=productService.fetchAll();
        model.addAttribute("product", products.stream().map(product ->
                Product.builder()
                        .productId(product.getProductId())
                        .imageBase64(getImageBase64(product.getImage()))
                        .productTitle(product.getProductTitle())
                        .productCategory(product.getProductCategory())
                        .productDescription(product.getProductDescription())
                        .productPrice(product.getProductPrice())
                        .build()

        ));
        return "adminproductlist";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

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


