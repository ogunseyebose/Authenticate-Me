package com.authenticate.FoodOrdering.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserRequest {
    private Long userId;
    private String email;
    private String password;
    private String accountStatus;
    private LocalDateTime dtCreated;
    private LocalDateTime dtModified;

}
