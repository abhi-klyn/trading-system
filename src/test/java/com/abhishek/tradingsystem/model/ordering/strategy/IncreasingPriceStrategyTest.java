package com.abhishek.tradingsystem.model.ordering.strategy;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class IncreasingPriceStrategyTest {
    BaseOrderingStrategy increasingPriceStrategy;
    Comparator<Order> orderComparator;

    @BeforeEach
    public void setUp() {
        increasingPriceStrategy = new IncreasingPriceStrategy();
        orderComparator = increasingPriceStrategy.getComparator();
    }

    @Test
    public void testDifferentPriceOrder() {
        Order order1 = generateRandomOrder();
        Order order2 = generateRandomOrder();
        int compareResult = orderComparator.compare(order1, order2);
        if (compareResult == 1) {
            assertThat("price", order1.getPrice(), greaterThan(order2.getPrice()));
        } else {
            assertThat("price", order2.getPrice(), greaterThan(order1.getPrice()));
        }
    }

    @Test
    public void testMarketOrderRegularOrder() {
        Order marketOrder = generateRandomOrder(null, null, null, null, OrderType.MARKET);
        Order limitOrder = generateRandomOrder(null, null, null, null, OrderType.LIMIT);
        int compareResult = orderComparator.compare(marketOrder, limitOrder);
        Assertions.assertEquals(1, compareResult);
    }

    @Test
    public void testSamePriceDifferentTime() {
        Order todayOrder = generateRandomOrder(new BigDecimal(10), null, LocalDateTime.now(), null, null);
        Order yesterdayOrder = generateRandomOrder(new BigDecimal(10), null, LocalDateTime.now().minus(Period.ofDays(1)),
                null, null);
        int compareResult = orderComparator.compare(todayOrder, yesterdayOrder);
        Assertions.assertEquals(1, compareResult);
    }

    private Order generateRandomOrder(BigDecimal price, Symbol symbol, LocalDateTime datetime,
                                      Operation operation, OrderType orderType) {
        Random random = new Random();
        Order newOrder = new Order(
                (price == null) ? new BigDecimal(random.nextInt()) : price,
                (symbol == null) ? Symbol.AAPL : symbol,
                random.nextInt(),
                (datetime == null) ? LocalDateTime.now() : datetime,
                (operation == null) ? Operation.BUY : operation,
                (orderType == null) ? OrderType.MARKET : orderType
        );
        return newOrder;
    }

    private Order generateRandomOrder() {
        return generateRandomOrder(null, null, null, null, null);
    }
}
