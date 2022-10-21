package com.authenticate.FoodOrdering.service;

import com.authenticate.FoodOrdering.dto.request.OrderRequest;
import com.authenticate.FoodOrdering.dto.response.Response;

public interface OrderService {

    Response placeOrder(OrderRequest orderRequest) ;
    Response viewOrdersByUser(Long userId);
/*
    Response viewOrderByStatus(String status);
*/
   /* void updateOrderStatus();*/


}