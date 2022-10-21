package com.authenticate.FoodOrdering.service.implementation;

import com.authenticate.FoodOrdering.dto.request.OrderRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import com.authenticate.FoodOrdering.model.Orders;
import com.authenticate.FoodOrdering.repository.OrderRepo;
import com.authenticate.FoodOrdering.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;
    Response resp= new Response();
    @Value("${completionTime}")
    private int waitingTime;
    @Override
    public Response placeOrder(OrderRequest orderRequest) {
        try{
            orderRequest.setOrderTime(LocalDateTime.now())
                    .setWaitingTime(LocalTime.of(00,waitingTime* orderRequest.getQuantity(),00));
            orderRequest.setAvailableTime(orderRequest.getOrderTime().plusMinutes(waitingTime* orderRequest.getQuantity()));

            Orders order= modelMapper.map(orderRequest, Orders.class);
            order.setIsCompleted('N');
            orderRepo.save(order);
            resp.setResp_Code("00");
            resp.setResp_Msg("You have successfully ordered "+ orderRequest.getQuantity());
            resp.setData(order.getOrderId());
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;
    }



    @Override
    public Response viewOrdersByUser(Long userId) {
        return null;
    }

    /*@Override
    public Response viewOrderByStatus(String status) {
        try{
            if(status.equalsIgnoreCase(String.valueOf(Status.COMPLETED))){
                final List<Orders> ordersList= orderRepo.findByIsCompleted('Y');
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
                final List<Orders> ordersList= orderRepo.findByIsCompleted('N');
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
    }*/

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
