package com.hgcode.bookstore.controller;

import com.hgcode.bookstore.model.Login;
import com.hgcode.bookstore.model.User;
import com.hgcode.bookstore.response.LoginResponse;
import com.hgcode.bookstore.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping("/users")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login){
        LoginResponse loginResponse = userService.loginUser(login);
        return ResponseEntity.ok(loginResponse);
    }
}
