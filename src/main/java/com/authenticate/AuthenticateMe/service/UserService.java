package com.authenticate.AuthenticateMe.service;


import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;

public interface UserService {

    Response signUp(UserRequest userRequest);

}
