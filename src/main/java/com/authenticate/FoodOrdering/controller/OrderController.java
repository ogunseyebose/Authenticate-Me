package com.authenticate.FoodOrdering.controller;

import com.authenticate.FoodOrdering.dto.request.OrderRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import com.authenticate.FoodOrdering.enums.Status;
import com.authenticate.FoodOrdering.exception.SaveException;
import com.authenticate.FoodOrdering.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/placeOrder")
    public ResponseEntity<Response> placeOrder(@RequestBody @Valid OrderRequest orderRequest) throws SaveException {
        return ResponseEntity.ok().body(orderService.placeOrder(orderRequest));
    }
    @GetMapping("/userOrders/{userId}")
    public ResponseEntity<Response> userOrders(@PathVariable ("userId") Long userId){
        return ResponseEntity.ok().body(orderService.viewOrdersByUser(userId));
    }
    @GetMapping("/totalUserOrders/{userId}")
    public ResponseEntity<Response> totalUserOrders(@PathVariable ("userId") Long userId){
        return ResponseEntity.ok().body(orderService.totalOrderPerUser(userId));
    }
    @GetMapping("/dashboard")
    public ResponseEntity<Response> dashboard(){
        return ResponseEntity.ok().body(orderService.viewOrderByStatus(String.valueOf(Status.PENDING)));
    }
    @GetMapping("/pending")
    public ResponseEntity<Response> getPendingOrders(){
        return ResponseEntity.ok().body(orderService.viewOrderByStatus(String.valueOf(Status.PENDING)));
    }
    @GetMapping("/completed")
    public ResponseEntity<Response> getCompletedOrders(){
        return ResponseEntity.ok().body(orderService.viewOrderByStatus(String.valueOf(Status.COMPLETED)));
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllOrders(){
        return ResponseEntity.ok().body(orderService.viewOrderByStatus(String.valueOf(Status.ALL)));
    }
}
