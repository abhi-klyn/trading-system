package com.abhishek.tradingsystem.model.ordering.strategy;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.OrderType;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Custom comparator class to be used for defining a comparator.
 * Market orders have the best price, hence are designed to be ahead in the list.
 * Followed by ordering based on price and time.
 * <p>
 * Note that the price comparison uses reverseOrder().
 */
public class DecreasingPriceStrategy implements BaseOrderingStrategy {
    private final static Map<OrderType, Integer> priority = Collections.singletonMap(OrderType.MARKET, 1);
    Comparator<Order> orderComparator;

    public DecreasingPriceStrategy() {
        orderComparator = Comparator.comparing(Order::getOrderType,
                Comparator.comparing(t -> priority.getOrDefault(t, 0)))
                .thenComparing(Order::getPrice, Comparator.reverseOrder())
                .thenComparing(Order::getDatetime)
                .thenComparing(Order::getOrderId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comparator<Order> getComparator() {
        return orderComparator;
    }
}
