package com.authenticate.AuthenticateMe.service;


import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Response signUp(UserRequest userRequest);

    Response login(UserRequest userRequest);

}
