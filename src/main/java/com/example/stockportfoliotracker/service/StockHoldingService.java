package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.config.StockPriceAPIConfig;
import com.example.stockportfoliotracker.model.Order;
import com.example.stockportfoliotracker.model.OrderType;
import com.example.stockportfoliotracker.model.StockHoldingDetails;
import com.example.stockportfoliotracker.model.StockPrice;
import com.example.stockportfoliotracker.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockHoldingService {
    private final OrderRepository orderRepository;
    private final RestClient stockPriceClient;
    private final StockPriceAPIConfig apiConfig;

    public List<StockHoldingDetails> getStockHoldingDetails() {
        // Retrieve all orders from the repository
        List<Order> orders = orderRepository.findAll();

        String uniqueStockSymbols = orders.stream().map(Order::stockSymbol).distinct().collect(Collectors.joining(","));

        Map<String,Double> stockPrices = getStockPriceMap(uniqueStockSymbols);

        // Process the list of orders to group by stock symbol and calculate the details for each symbol
        return orders.stream()
                // Group orders by stock symbol
                .collect(Collectors.groupingBy(Order::stockSymbol))
                // Create a stream of the entry set (key-value pairs) of the resulting map
                .entrySet().stream()
                // Map each entry of the map to a StockOrderDetails object
                .map(entry ->{
                    // For each stock symbol (entry.getKey()) create a StockHoldingDetails object
                    return populateStockHolding(entry, stockPrices);
                })
                // Collect the results of the mapping into a list
                .toList();
    }

    private static StockHoldingDetails populateStockHolding(Map.Entry<String, List<Order>> entry, Map<String, Double> stockPrices) {
        // Calculate the total quantity of stocks, adjusting for sells (negative quantities)
        double quantity = entry.getValue().stream().mapToDouble(order -> order.quantity() * (order.orderType() == OrderType.SELL ? -1 : 1)).sum();
        double price = stockPrices.get(entry.getKey());
        double marketValue = quantity * price;

        return new StockHoldingDetails(
                entry.getKey(),
                quantity,
                marketValue,
                price
        );
    }

    private Map<String,Double> getStockPriceMap(String stockSymbol) {
        return Objects.requireNonNull(stockPriceClient.get()
                        .uri("/" + stockSymbol + "?apikey=" + apiConfig.getApiKey())
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<StockPrice>>() {
                        }))
                .stream()
                .collect(Collectors.toMap(StockPrice::symbol, StockPrice::price));
    }
}
