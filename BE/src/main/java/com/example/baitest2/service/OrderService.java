package com.example.baitest2.service;


import com.example.baitest2.dto.CreateOrderCommand;
import com.example.baitest2.dto.OrderResponse;
import com.example.baitest2.entity.User;

import java.util.List;

public interface OrderService {

    Long createOrder(User user, CreateOrderCommand command);

    List<OrderResponse> getAllUserOrders(Long userId);

}
