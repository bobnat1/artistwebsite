package com.project.artistwebsite.controller;

import com.project.artistwebsite.dto.UserDTO;
import com.project.artistwebsite.model.Mix;
import com.project.artistwebsite.model.Posts;
import com.project.artistwebsite.model.User;
import com.project.artistwebsite.repository.MixRepository;
import com.project.artistwebsite.repository.PostsRepository;
import com.project.artistwebsite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MixRepository mixRepository;

    @Autowired
    private PostsRepository postsRepository;

    // Brings user to main page of website
    @GetMapping("/")
    public String frontPage() {

        return "HTML/index";
    }

    // Submits user registrations and saves to database
    @PostMapping("/process-user")
    public String loginRegister(@Valid @ModelAttribute (name = "user") UserDTO userDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "HTML/loginregister";
        }

        if (userService.findUserByEmail(userDTO.getEmail()) != null){

            model.addAttribute("errorMsg", "This email is already in use");
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

    // shows user error on login page
    @RequestMapping("/error-login")
    public String loginError(Model model) {
        model.addAttribute("error", "Invalid email and/or password.");
        return "HTML/login";
    }

    // Brings user to the contact page
    @RequestMapping("/dj-contact")
    public String djContactPage() {
        return "HTML/contact";
    }


    // Brings User role Admin to the admin home page
    @RequestMapping("/page-admin")
    public String getAdminPage(){
        return "HTML/admin-page";
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

    @RequestMapping("/delete-profile")
    public String deleteProfile(@RequestParam("userId") Integer userId) {
        userService.deleteUser(userId);
        return "redirect:/logout";
    }

    // Brings Authenticated user to their home page, and posts current mixes saves to database for user to download
    @RequestMapping("/main-account")
    public String userMainAccountPage(Model model, Authentication authentication) {
        Iterable<Mix> mixes = mixRepository.findAll();
        Iterable<Posts> posts = postsRepository.findAll();
        String email = authentication.getName();
        model.addAttribute("mixes", mixes);
        model.addAttribute("posts", posts);
        model.addAttribute("email", email);
        model.addAttribute("userPreferences", userService.returnUserPreferences(email));
        model.addAttribute("user", userService.returnUserSummary(email));
        return "HTML/account-main2";
    }

    // Changes user's password
    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute (name ="password") String password, @ModelAttribute (name ="newPassword") String newPassword, Authentication authentication, Model model, RedirectAttributes redirectAttributes){

            String email = authentication.getName();
        try {
            userService.changeUserPassword(password, newPassword, email);
            redirectAttributes.addFlashAttribute("successMessage", "Password Successfully Updated!");
            return "redirect:/main-account";
        } catch (IllegalArgumentException e) {
            String errorMessage = e.toString();
            redirectAttributes.addFlashAttribute("errorMessage", "Incorrect Password");
            return "redirect:/main-account";
        }
    }

    // Updates user preferences on receiving emails
    @PostMapping("/get-emails")
    public String receiveEmails(@Valid @ModelAttribute (name = "getEmails") boolean getEmails, Authentication authentication) {
        System.out.println(getEmails);
        String email = authentication.getName();
        userService.updateUserPrefEmail(getEmails, email);
        return "redirect:/main-account";
    }
}
