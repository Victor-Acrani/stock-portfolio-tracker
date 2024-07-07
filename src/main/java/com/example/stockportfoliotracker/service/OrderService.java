package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.model.Order;
import com.example.stockportfoliotracker.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        Order orderWithDate = new Order(
                null,
                order.user_id(),
                order.stockSymbol(),
                order.quantity(),
                order.price(),
                order.orderType(),
                java.time.LocalDateTime.now()
        );
        return orderRepository.save(orderWithDate);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
