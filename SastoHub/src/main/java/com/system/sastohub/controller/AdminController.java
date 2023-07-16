package com.system.sastohub.controller;

import com.system.sastohub.entity.Contact;
import com.system.sastohub.entity.Order;
import com.system.sastohub.entity.Product;

import com.system.sastohub.pojo.ProductPojo;
import com.system.sastohub.repo.OrderRepo;
import com.system.sastohub.services.ContactService;
import com.system.sastohub.services.OrderService;
import com.system.sastohub.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;
    private final ContactService contactService;

    @GetMapping("/{id}")
    public String fetchById(@PathVariable Integer id, Model model, Principal principal){
        Product product= productService.fetchById(id);
        model.addAttribute("product", product);
        return "myproduct";
    }



    @GetMapping("/list")
    public String getproductList(Model model){
        List<Product> adminproduct=productService.fetchAll();
        model.addAttribute("product", adminproduct.stream().map(product ->
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

    @GetMapping("/contact")
    public String createcontact(Model model) {
        List<Contact> admincontact = contactService.fetchAll();
        model.addAttribute("contact", admincontact.stream().map(contact ->
                Contact.builder()
                        .contactId(contact.getContactId())
                        .contactname(contact.getContactname())
                        .contactemail(contact.getContactemail())
                        .contactsubject(contact.getContactsubject())
                        .contactmessage(contact.getContactmessage())
                        .build()
        ));
        return "visualization";
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

    @GetMapping("/product/{id}")
    public String fetchById(@PathVariable Integer id, Model model){
        Product product= productService.fetchById(id);
        model.addAttribute("products", new ProductPojo(product));
//        model.addAttribute("productdata", productService.fetchById(principal.getName())
        model.addAttribute("product", product);
        return "deleteproduct";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model){
        Product product =productService.fetchById(id);
        model.addAttribute("product", new ProductPojo(product));
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.deleteById(id);
        return "redirect:/product/list";
    }
//
//    @GetMapping("/orderList")
//    public String getOrderList(Model model){
//        List <Order> orderLists=orderService.fetchAll();
//        List <Order> orderList=orderService.fetchAll();
////        model.addAttribute("orderList", orderLists);
//        model.addAttribute("orders", orderList);
////        model.addAttribute("order", orderLists.stream().map(orders ->
////                Order.builder()
////                        .orderId(orders.getOrderId())
////                        .full_name(orders.getFull_name())
////                        .productTitle(orders.getProductTitle())
////                        .quantity(orders.getQuantity())
////                        .productPrice(orders.getProductPrice())
////                        .productCategory(orders.getProductCategory())
////                        .address(orders.getAddress())
////                        .mobileNo(orders.getMobileNo())
////                        .size(orders.getSize())
////                        .email(orders.getEmail())
////                        .build()
////        ));
//        return "Admindashboard";
//    }

    @GetMapping("/Admin-dashboard")
    public String admin(Model model) {
        List<Order> orderLists=orderService.fetchAll();
        model.addAttribute("orderLists", orderLists);
        return "Admindashboard";
    }
}

