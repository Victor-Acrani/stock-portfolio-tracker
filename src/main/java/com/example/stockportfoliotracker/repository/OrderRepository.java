package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.model.Order;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<Order, Long> {
}
