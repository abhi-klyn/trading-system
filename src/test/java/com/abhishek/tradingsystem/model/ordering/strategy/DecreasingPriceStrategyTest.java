package com.abhishek.tradingsystem.model.ordering.strategy;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

/**
 * Tests to verify the functionality of the DecreasingPriceStrategy Comparator
 */
public class DecreasingPriceStrategyTest {
    BaseOrderingStrategy decreasingPriceStrategy;
    Comparator<Order> orderComparator;

    @BeforeEach
    public void setUp() {
        decreasingPriceStrategy = new DecreasingPriceStrategy();
        orderComparator = decreasingPriceStrategy.getComparator();
    }

    /**
     * Generates two random orders with random prices, both the orders have same properties, except the price.
     * If the comparator returns 1, the tests asserts that the price of first order was less than the second.
     */
    @Test
    public void testDifferentPriceOrder() {
        Order order1 = Utils.generateRandomOrder();
        Order order2 = Utils.generateRandomOrder();
        int compareResult = orderComparator.compare(order1, order2);
        if (compareResult == 1) {
            assertThat("price", order1.getPrice(), lessThan(order2.getPrice()));
        } else {
            assertThat("price", order2.getPrice(), lessThan(order1.getPrice()));
        }
    }

    /**
     * Tests that market order is ahead of limit order.
     * Market order and limit order are created, and it is verified that the comparator moves market order ahead.
     */
    @Test
    public void testMarketOrderRegularOrder() {
        Order marketOrder = Utils.generateRandomOrder(null, null, null, null,
                OrderType.MARKET);
        Order limitOrder = Utils.generateRandomOrder(null, null, null, null,
                OrderType.LIMIT);
        int compareResult = orderComparator.compare(marketOrder, limitOrder);
        Assertions.assertEquals(1, compareResult);
    }

    /**
     * Tests if the prices of two orders were same, comparator compares based on time.
     * Two orders are created, one of them today other one, yesterday.
     * test asserts if the comparator compares the orders correctly.
     */

}
    @Test
    public void testSamePriceDifferentTime() {
        Random random = new Random();
        int price = random.nextInt(100);
        Order todayOrder = Utils.generateRandomOrder(new BigDecimal(price), null, LocalDateTime.now(),
                null, null);
        Order yesterdayOrder = Utils.generateRandomOrder(new BigDecimal(price), null,
                LocalDateTime.now().minus(Period.ofDays(1)), null, null);
        int compareResult = orderComparator.compare(todayOrder, yesterdayOrder);
        Assertions.assertEquals(1, compareResult);
    }