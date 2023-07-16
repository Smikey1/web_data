package com.system.sastohub.controller;

import com.system.sastohub.entity.Order;
import com.system.sastohub.entity.Product;
import com.system.sastohub.entity.User;
import com.system.sastohub.pojo.ContactPojo;
import com.system.sastohub.pojo.ProductPojo;
import com.system.sastohub.services.ContactService;
import com.system.sastohub.services.OrderService;
import com.system.sastohub.services.UserServices;
import com.system.sastohub.pojo.UserPojo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;
    private final OrderService orderService;
    private final ContactService contactService;




    @GetMapping("/bought")
    public String store(){
        return "boughtproducts";
    }

    @GetMapping("/stat")
    public String stat() {
        return "visualization";
    }

    @GetMapping("/about")
    public String about() {
        return "aboutus";
    }

    @GetMapping("/faq")
    public String faq() {
        return "FAQ";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserPojo());

        return "Signup";
    }

    @GetMapping("/contactus")
    public String contact(Model model) {
        model.addAttribute("contact", new ContactPojo());
        return "Contactus";
    }

    @PostMapping("/savecontact")
    public String save(@Valid ContactPojo contactPojo) {
        contactService.save(contactPojo);
        return "redirect:/home/homepage";
    }


    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home/homepage";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo) {
        userServices.save(userPojo);
        return "redirect:/user/login";
    }


    @GetMapping("/profile")
    public String profile() {
        return "updateprofile";
    }

    @GetMapping("/myproduct")
    public String myproduct() {
        return "myproduct";
    }


    @GetMapping("/profile/{id}")
    public String getUserProfiile(@PathVariable("id") Integer id, Model model ){
        User user = userServices.fetchById(id);
        model.addAttribute("users", new UserPojo(user));
//        model.addAttribute("user", userServices.findByEmail(principal.getName()));
        model.addAttribute("currentUser", user);
        return "updateprofile";
    }


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user =userServices.fetchById(id);
        model.addAttribute("currentUser", new UserPojo(user));
        return "redirect:/user/profile/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userServices.deleteById(id);
        return "redirect:/login";
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model){
        model.addAttribute("users",new UserPojo());
        return ("Forgetpasword");
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid UserPojo userPojo){
        userServices.processPasswordResetRequest(userPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/home";
    }

    @GetMapping("/booked")
    public String fetchAllbooking(){
        return "Ordereditems";


    }

    @GetMapping("/booked/{id}")
    public String fetchAllbook(@PathVariable("id") Integer id, Model model , Principal principal){
        List<Order> order= orderService.findBookingById(id);
        model.addAttribute("books",order);
        model.addAttribute("userdata",userServices.findByEmail(principal.getName()));

        return "Ordereditems";
    }
}
