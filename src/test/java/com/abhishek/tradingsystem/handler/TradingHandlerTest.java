package com.abhishek.tradingsystem.handler;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import com.abhishek.tradingsystem.model.order.operations.BaseOrderOperation;
import com.abhishek.tradingsystem.model.order.operations.SellOrders;
import com.abhishek.tradingsystem.model.ordering.strategy.DecreasingPriceStrategy;
import com.abhishek.tradingsystem.model.ordering.strategy.IncreasingPriceStrategy;
import com.abhishek.tradingsystem.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class TradingHandlerTest {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAddOrder() {
        Random random = new Random();
        String orderId = TradingHandler.addOrder(
                new BigDecimal(11.22),
                Symbol.AAPL,
                random.nextInt(),
                LocalDateTime.now(),
                Operation.BUY,
                OrderType.MARKET
        );
        Assertions.assertNotNull(orderId);
    }

    @Test
    public void testRemoveOrder() {
        Random random = new Random();
        String orderId = TradingHandler.addOrder(
                new BigDecimal(11.22),
                Symbol.AAPL,
                random.nextInt(),
                LocalDateTime.now(),
                Operation.BUY,
                OrderType.MARKET
        );
        Assertions.assertNotNull(orderId);
        TradingHandler.removeOrder(orderId);
        List<Order> orderList = TradingHandler.listOrders(Operation.BUY);
        for(Order order: orderList) {
            Assertions.assertNotEquals(orderId, order.getOrderId());
        }
    }

    @Test
    public void testListOrders() {
        addRandomSellOrders();
        List<Order> orderList = TradingHandler.listOrders(Operation.SELL);
        Assertions.assertNotNull(orderList);
        Comparator<Order> orderComparator = (new IncreasingPriceStrategy()).getComparator();
        Order prev = null;
        for(Order current : orderList) {
            if(prev != null && orderComparator.compare(prev, current) > 0) {
                fail("Ordering is not correct");
            }
            prev = current;
        }
    }

    private void addRandomSellOrders() {
        Random random = new Random();
        int numberOfOrders = random.nextInt(45) + 5; // random number of orders between 5- 50
        for(int i = 0; i < numberOfOrders; i++) {
            TradingHandler.addOrder(Utils.generateRandomOrder(null, null, null, Operation.SELL, null));
        }
    }
}
