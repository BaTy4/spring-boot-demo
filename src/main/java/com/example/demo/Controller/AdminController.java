package com.example.demo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {
    @GetMapping("/dashboard/admin")
    public String messages(
            Model model,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletRequest request
    ) {
        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
        model.addAttribute("username", userDetails.getUsername());
        return "admin";
    }
}
