package com.authenticate.AuthenticateMe.controller;

import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;
import com.authenticate.AuthenticateMe.service.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class userController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Response> signUp(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.signUp(userRequest));
    }

}
