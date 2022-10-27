package com.authenticate.FoodOrdering.service.implementation;

import com.authenticate.FoodOrdering.config.EmailService;
import com.authenticate.FoodOrdering.dto.request.OrderRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import com.authenticate.FoodOrdering.enums.Status;
import com.authenticate.FoodOrdering.model.Orders;
import com.authenticate.FoodOrdering.model.User;
import com.authenticate.FoodOrdering.repository.OrderRepo;
import com.authenticate.FoodOrdering.repository.UserRepo;
import com.authenticate.FoodOrdering.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    Response resp= new Response();
    @Value("${completionTime}")
    private int waitingTime;

    @Value("${order-mail}")
    private String orderSender;

    @Value("${ordercontent}")
    private String orderContent;

    @Value("${orderSubject}")
    private String orderSubject;
    @Override
    public Response placeOrder(OrderRequest orderRequest) {
        try{
            Optional<User> user= userRepo.findById(orderRequest.getUserId());
            if(user.isPresent()){
                log.info("Date "+ LocalDateTime.now());
            orderRequest
                    .setOrderTime(LocalDateTime.now())
                    .setWaitingTime(LocalTime.of(00,waitingTime* orderRequest.getQuantity(),00))
                    .setAvailableTime(orderRequest.getOrderTime().plusMinutes(waitingTime* orderRequest.getQuantity()));

            Orders order= modelMapper.map(orderRequest, Orders.class);
            order.setIsCompleted('N');
            orderRepo.save(order);
            resp.setResp_Code("00");
            resp.setResp_Msg("SUCCESS");
            emailService.sendmail(orderSender, user.get().getEmail(), orderSubject, orderContent+orderRequest.getQuantity()+"Toast bread");

            resp.setData(order.getOrderId());
            }
            else{
                resp.setResp_Code("84");
                resp.setResp_Msg("User does not exist");

            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;
    }



    @Override
    public Response viewOrdersByUser(Long userId) {
        List<Orders> userOrder= orderRepo.findByUserId(userId);
        if(userOrder.isEmpty()){
            resp.setResp_Code("88");
            resp.setResp_Msg("User Did not place an order");
        }
        else{
            resp.setResp_Code("00");
            resp.setResp_Msg("SUCCESS");
            resp.setData(userOrder);
        }


        return resp;
    }

    @Override
    public  Response totalOrderPerUser(Long userId) {
        Long totalOrders= Long.valueOf(0);
        resp= viewOrdersByUser(userId);
        List<Orders> orders= (List<Orders>) resp.getData();
        for (int i=0; i<orders.size(); i++){
            totalOrders+=orders.get(i).getQuantity();
        }
        Response response = new Response();
        response.setData(totalOrders);
        response.setResp_Msg(resp.getResp_Msg());
        response.setResp_Code(resp.getResp_Code());
        return response;
    }

    /*@Override
    public Response totalOrders() {
        List<Orders> allOrders= orderRepo.findAll();
        if(allOrders.isEmpty()){
            resp.setResp_Code("84");
            resp.setResp_Msg("No orders ");
        }
        else{
            resp.setResp_Code("00");
            resp.setResp_Msg("Success ");
            resp.setData(allOrders);
        }
        return resp;
    }*/


    @Override
    public Response viewOrderByStatus(String status) {
        Response resp= new Response();
        try{
            if(status.equalsIgnoreCase(String.valueOf(Status.COMPLETED))){
               List<Orders> ordersList= orderRepo.findByIsCompletedOrderByIsCompletedAsc('Y', Sort.by(Sort.Direction.ASC));
                if(ordersList.isEmpty()){
                    resp.setResp_Code("82");
                    resp.setResp_Msg("No order is completed");
                }
                else{
                    resp.setResp_Code("00");
                    resp.setResp_Msg("Success");
                    resp.setData(ordersList);
                }
            }
            else if(status.equalsIgnoreCase(String.valueOf(Status.PENDING))){
                final List<Orders> ordersList= orderRepo.findByIsCompletedOrderByIsCompletedAsc('N', Sort.by(Sort.Direction.ASC));

                if(ordersList.isEmpty()){
                    resp.setResp_Code("82");
                    resp.setResp_Msg("No order is PENDING");
                }
                else{
                    resp.setResp_Code("00");
                    resp.setResp_Msg("Success");
                    resp.setData(ordersList);
                }
            }
            else if(status.equalsIgnoreCase(String.valueOf(Status.ALL))){
                final List<Orders> ordersList= orderRepo.findAll();
                if(ordersList.isEmpty()){
                    resp.setResp_Code("82");
                    resp.setResp_Msg("No order available");
                }
                else{
                    resp.setResp_Code("00");
                    resp.setResp_Msg("Success");
                    resp.setData(ordersList);
                }
            }
            else{
                resp.setResp_Code("42");
                resp.setResp_Msg("Wrong Status");
            }
        }catch (Exception e){

        }
        return resp;
    }

   /* @Override
    @Scheduled(fixedRate = 1000)
    public void updateOrderStatus() {
        try{
            List<Orders> pendingOrders= (List<Orders>) viewOrderByStatus("Completed").getData();
            for(int i=0; i< pendingOrders.size();i++){


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }*/
}
