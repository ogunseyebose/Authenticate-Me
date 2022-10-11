/*
package com.authenticate.FoodOrdering.repository;

import com.authenticate.FoodOrdering.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
            @Query("Select c from Orders where c.isCompleted=:isCompleted")
            List<Orders> findByIsCompleted(Character isCompleted);

}
*/
