package com.authenticate.FoodOrdering.repository;

import com.authenticate.FoodOrdering.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepo extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
    List<Orders> findByIsCompleted(Character isCompleted);


}
