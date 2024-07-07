package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.model.Order;
import com.example.stockportfoliotracker.model.OrderType;
import com.example.stockportfoliotracker.model.StockOrderDetails;
import com.example.stockportfoliotracker.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockHoldingService {
    private final OrderRepository orderRepository;

    public List<StockOrderDetails> getStockHoldingDetails() {
        // Retrieve all orders from the repository
        List<Order> orders = orderRepository.findAll();

        // Process the list of orders to group by stock symbol and calculate the details for each symbol
        List<StockOrderDetails> stockOrderDetails = orders.stream()
                // Group orders by stock symbol
                .collect(Collectors.groupingBy(Order::stockSymbol))
                // Create a stream of the entry set (key-value pairs) of the resulting map
                .entrySet().stream()
                // Map each entry of the map to a StockOrderDetails object
                .map(entry ->
                        // For each stock symbol (entry.getKey()), calculate the total quantity and create a StockOrderDetails object
                        new StockOrderDetails(
                                entry.getKey(),
                                // Calculate the total quantity of stocks, adjusting for sells (negative quantities)
                                entry.getValue().stream()
                                        .mapToDouble(order -> order.quantity() * (order.orderType() == OrderType.SELL ? -1 : 1))
                                        .sum(),
                                0,
                                0
                        )
                )
                // Collect the results of the mapping into a list
                .collect(Collectors.toList());

        // Return the list of StockOrderDetails
        return stockOrderDetails;
    }
}
