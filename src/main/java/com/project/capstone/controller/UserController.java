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

import java.util.List;

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

    @RequestMapping("/dj-contact")
    public String djContactPage() {
        return "HTML-JS-SBA/contact";
    }

    @RequestMapping("/dj-mix")
    public String djMixPage() {
        return "HTML-JS-SBA/offthetop";
    }

    @RequestMapping("/page-admin")
    public String getAdminPage(){
        return "HTML-JS-SBA/admin-page";
    }

    @RequestMapping("/confirm-message")
    public String confirmMessage() {
        return "HTML-JS-SBA/message-confirmation";
    }

    @RequestMapping("/user-edit")
    public String editUsers(Model model) {
        Iterable<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "HTML-JS-SBA/edit-users";
    }
    @RequestMapping("/change-role")
    public String processChange(@RequestParam("userId") Integer userId, @RequestParam("newRole") Integer newRole) {
        userService.changeRoles(userId, newRole);
        return "redirect:/page-admin";
    }

    @RequestMapping("/user-delete")
    public String deleteUsers(@RequestParam("userId") Integer userId) {
        userService.deleteUser(userId);
        return "redirect:/user-edit";
    }

}
