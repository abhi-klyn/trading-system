package com.abhishek.tradingsystem;

import com.abhishek.tradingsystem.model.TradingService;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class TradingsystemApplicationTests {
    @Test
    void setUp() {
        TradingService tradingService = new TradingService();
        Assertions.assertNotNull(tradingService.addOrder(new BigDecimal(10), Symbol.AAPL, 2, LocalDateTime.now(), Operation.SELL, OrderType.MARKET));
        System.out.println(tradingService.listOrders(Operation.SELL));
        Assertions.assertNotNull(tradingService.addOrder(new BigDecimal(20), Symbol.AAPL, 2, LocalDateTime.now(), Operation.SELL, OrderType.MARKET));
        System.out.println(tradingService.listOrders(Operation.SELL));
    }

}
