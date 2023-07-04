package com.project.capstone.controller;

import com.project.capstone.dto.UserDTO;
import com.project.capstone.model.Message;
import com.project.capstone.model.Mix;
import com.project.capstone.model.User;
import com.project.capstone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String frontPage() {

        return "HTML-JS-SBA/index";
    }

    @PostMapping("/process-user")
    public String loginRegister(@Valid @ModelAttribute (name = "user") UserDTO userDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "HTML-JS-SBA/loginregister";
        }

        if (userService.findUserByEmail(userDTO.getEmail()) == null){
            userService.saveUser(userDTO);

            return "HTML-JS-SBA/confirmation-page";
        }
        else
            return "HTML-JS-SBA/loginregister";
    }

//    @GetMapping("/main-account")
//    public String mainAccount(Model model, Authentication authentication) {
//
//        User user = userService.findUserByEmail(authentication.getName());
//        model.addAttribute("user", user);
//
//        return "account-main";
//    }

    @RequestMapping("/user-home")
    public String processUserLogin(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")){
            return "redirect:/page-admin";
        }
        return "redirect:/main-account";
    }

    @GetMapping("/register-user")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "HTML-JS-SBA/loginregister";
    }

    @RequestMapping("/login-user")
    public String loginUser() {
        return "HTML-JS-SBA/login";
    }

    @RequestMapping("/main-account")
    public String userMainAccountPage() {
        return "HTML-JS-SBA/account-main";
    }

    @RequestMapping("/dj-contact")
    public String djContactPage() {
        return "HTML-JS-SBA/contact";
    }

    @RequestMapping("/dj-mix")
    public String djMixPage() {
        return "HTML-JS-SBA/offthetop";
    }

    @RequestMapping("/message-dj")
    public String messageScreen(Model model) {
        model.addAttribute("message", new Message());
        return "HTML-JS-SBA/message";
    }

    @RequestMapping("/page-admin")
    public String getAdminPage(){
        return "HTML-JS-SBA/admin-page";
    }
    @RequestMapping("/post-mix")
    public String uploadMix(Model model) {
        model.addAttribute("mix", new Mix());
        return "HTML-JS-SBA/mix-page";
    }

    @RequestMapping("/confirm-message")
    public String confirmMessage() {
        return "HTML-JS-SBA/message-confirmation";
    }
}
