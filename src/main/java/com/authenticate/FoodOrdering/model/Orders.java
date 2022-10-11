package com.authenticate.FoodOrdering.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    private Long userId;
    private Long quantity;
    private LocalTime orderTime;
    private LocalTime waitingTime;
    private LocalDateTime availableTime;
    private Character isCompleted;


}
