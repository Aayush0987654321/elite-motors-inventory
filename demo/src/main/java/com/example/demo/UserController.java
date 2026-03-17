package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/api/me")
    public String getCurrentUser(Principal principal) {
        if (principal == null) return ""; 
        return principal.getName(); // This returns "admin" or whatever username was used
    }
}
