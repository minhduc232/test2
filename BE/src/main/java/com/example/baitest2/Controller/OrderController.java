package com.example.baitest2.Controller;

import com.example.baitest2.dto.CreateOrderCommand;
import com.example.baitest2.entity.User;
import com.example.baitest2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/me/orders")
    public ResponseEntity<?> getAllOrder() {
        var userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        var responses = orderService.getAllUserOrders(userId);
        return (responses.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderCommand command) {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var orderId = orderService.createOrder(user, command);
        return ResponseEntity.created(URI.create(String.valueOf(orderId))).build();
    }

}
