package com.abhishek.tradingsystem;

import com.abhishek.tradingsystem.model.TradingService;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dummy test class created to play with the Application and see the results.
 * As the orders class is annotated with Lombok's @toString, we can see the elements.
 *
 * For real tests check {@link com.abhishek.tradingsystem.handler.TradingHandlerTest}
 * {@link com.abhishek.tradingsystem.model.ordering.strategy.DecreasingPriceStrategyTest}
 */
class TradingsystemApplicationTests {
    @Test
    void randomTest() {
        TradingService tradingService = new TradingService();
        Assertions.assertNotNull(tradingService.addOrder(new BigDecimal(10), Symbol.AAPL, 2, LocalDateTime.now(), Operation.SELL, OrderType.MARKET));
        System.out.println(tradingService.listOrders(Operation.SELL));
        Assertions.assertNotNull(tradingService.addOrder(new BigDecimal(20), Symbol.AAPL, 2, LocalDateTime.now(), Operation.SELL, OrderType.MARKET));
        System.out.println(tradingService.listOrders(Operation.SELL));
    }

}
