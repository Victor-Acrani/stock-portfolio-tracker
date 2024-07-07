package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.model.StockHoldingDetails;
import com.example.stockportfoliotracker.service.StockHoldingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock-holdings")
@AllArgsConstructor
public class StockHoldingController {
    private final StockHoldingService stockHoldingService;

    @GetMapping
    public List<StockHoldingDetails> getStockHoldings() {
        return stockHoldingService.getStockHoldingDetails();
    }

}
