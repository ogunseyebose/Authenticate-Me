package com.authenticate.FoodOrdering.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long userId;
    private String email;
    private String password;

}
