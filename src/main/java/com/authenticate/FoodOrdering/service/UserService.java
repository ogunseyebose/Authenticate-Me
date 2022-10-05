package com.authenticate.FoodOrdering.service;


import com.authenticate.FoodOrdering.dto.request.UserRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Response signUp(UserRequest userRequest);

    Response login(UserRequest userRequest);

}
