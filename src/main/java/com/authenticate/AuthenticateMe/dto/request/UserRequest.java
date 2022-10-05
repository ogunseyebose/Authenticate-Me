package com.authenticate.AuthenticateMe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserRequest {
    private Long userId;
    private String email;
    private String password;

}
