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

    // Brings user to main page of website
    @GetMapping("/")
    public String frontPage() {

        return "HTML/index";
    }

    // Submits user registrations and saves to database
    @PostMapping("/process-user")
    public String loginRegister(@Valid @ModelAttribute (name = "user") UserDTO userDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "HTML/loginregister";
        }

        if (userService.findUserByEmail(userDTO.getEmail()) == null){
            userService.saveUser(userDTO);

            return "HTML/confirmation-page";
        }
        else
            return "HTML/loginregister";
    }

    // Brings user to authenticated User role's home page
    @RequestMapping("/user-home")
    public String processUserLogin(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")){
            return "redirect:/page-admin";
        }
        return "redirect:/main-account";
    }

    // Brings user to register at registration page, loads UserDTO model on page
    @GetMapping("/register-user")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "HTML/loginregister";
    }

    // Brings user to the login page
    @RequestMapping("/login-user")
    public String loginUser() {
        return "HTML/login";
    }

    // Brings user to the contact page
    @RequestMapping("/dj-contact")
    public String djContactPage() {
        return "HTML/contact";
    }

    // Brings user to the page with mixes posted
    @RequestMapping("/dj-mix")
    public String djMixPage() {
        return "HTML/offthetop";
    }

    // Brings User role Admin to the admin home page
    @RequestMapping("/page-admin")
    public String getAdminPage(){
        return "HTML/admin-page";
    }

    // Brings user to message sent confirmation page
    @RequestMapping("/confirm-message")
    public String confirmMessage() {
        return "HTML/message-confirmation";
    }

    // Brings User role Admin to user management page
    @RequestMapping("/user-edit")
    public String editUsers(Model model) {
        Iterable<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "HTML/edit-users";
    }

    // On user-edit page if Admin changes user role then this will process that request
    @RequestMapping("/change-role")
    public String processChange(@RequestParam("userId") Integer userId, @RequestParam("newRole") Integer newRole) {
        userService.changeRoles(userId, newRole);
        return "redirect:/page-admin";
    }

    // On ussr-edit page if Admin deletes user then this will process that request
    @RequestMapping("/user-delete")
    public String deleteUsers(@RequestParam("userId") Integer userId) {
        userService.deleteUser(userId);
        return "redirect:/user-edit";
    }

}
