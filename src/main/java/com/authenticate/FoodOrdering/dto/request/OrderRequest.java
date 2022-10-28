package com.authenticate.FoodOrdering.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Accessors(chain = true)
public class OrderRequest {
    private Long orderId;
    private Long userId;
    private int quantity;
    private LocalDateTime orderTime;
    private LocalTime waitingTime;
    private LocalDateTime availableTime;


}
