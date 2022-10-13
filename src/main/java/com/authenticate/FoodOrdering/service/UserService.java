package com.authenticate.FoodOrdering.service;


import com.authenticate.FoodOrdering.dto.request.UserRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Response signUp(UserRequest userRequest);

    ResponseEntity<Object> login(UserRequest userRequest);

}
