package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "logins";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, 
                              @RequestParam String password,
                              @RequestParam(required = false) boolean remember,
                              RedirectAttributes redirectAttributes) {
        System.out.println("Login for user: " + username);
        User authenticatedUser = loginService.authenticateUser(username, password);
        
        if (authenticatedUser != null) {
            redirectAttributes.addFlashAttribute("message", "Welcome, " + authenticatedUser.getFirstName() + "!");
            redirectAttributes.addFlashAttribute("user", authenticatedUser);
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully.");
        return "redirect:/login";
    }
}
