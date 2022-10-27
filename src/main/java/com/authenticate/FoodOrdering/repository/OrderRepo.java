package com.authenticate.FoodOrdering.repository;

import com.authenticate.FoodOrdering.model.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Orders, Long> {
    List<Orders> findByIsCompletedOrderByAvailableTimeAsc(@NonNull Character isCompleted, Sort sort);
              List<Orders> findByUserId(Long userId);

    List<Orders> findByIsCompletedOrderByIsCompletedAsc(@NonNull Character isCompleted, Sort sort);

}
