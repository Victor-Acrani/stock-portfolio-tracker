package com.example.stockportfoliotracker.model;

public record StockOrderDetails(String stockSymbol, double quantity, double currentMarketValue, double currentMarketPrice) {
}
