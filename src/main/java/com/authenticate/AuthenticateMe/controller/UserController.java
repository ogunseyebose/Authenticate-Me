package com.authenticate.AuthenticateMe.controller;


import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;
import com.authenticate.AuthenticateMe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Response> signUp(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.signUp(userRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.login(userRequest));
    }

}
