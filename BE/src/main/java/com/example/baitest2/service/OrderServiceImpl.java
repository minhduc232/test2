package com.example.baitest2.service;

import com.example.baitest2.Repository.OrderRepository;
import com.example.baitest2.dto.CreateOrderCommand;
import com.example.baitest2.dto.OrderResponse;
import com.example.baitest2.entity.Order;
import com.example.baitest2.entity.User;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long createOrder(User user, CreateOrderCommand command) {
        Order order = modelMapper.map(command, Order.class);
        order.setUser(user);
        return orderRepository.save(order).getId();
    }


    @Override
    public List<OrderResponse> getAllUserOrders(Long userId) {
        return orderRepository.findAll()
                .stream().map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }



}
