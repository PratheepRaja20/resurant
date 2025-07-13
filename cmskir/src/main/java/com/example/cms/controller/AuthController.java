
package com.example.cms.controller;

import com.example.cms.dto.LoginDTO;
import com.example.cms.dto.UserDTO;
import com.example.cms.model.User;
import com.example.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        logger.info("Registering user: {}", userDTO.getUsername());
        String response = userService.registerUser(userDTO);
        logger.debug("Registration response: {}", response);
        return response;
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO) {
        logger.info("Login attempt for user: {}", loginDTO.getUsername());
        User user = userService.loginUser(loginDTO);

        if (user != null) {
            logger.info("Login successful for user: {}", user.getUsername());
            return user;
        }

        logger.warn("Login failed for username: {}", loginDTO.getUsername());
        return "Invalid username or password";
    }
}

