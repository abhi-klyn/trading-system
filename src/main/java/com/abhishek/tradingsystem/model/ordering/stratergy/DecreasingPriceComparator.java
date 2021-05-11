package com.abhishek.tradingsystem.model.ordering.stratergy;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.OrderType;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DecreasingPriceComparator extends OrderingStratergy {
    private final static Map<OrderType, Integer> priority = new HashMap<>();

    public DecreasingPriceComparator() {
        priority.put(OrderType.MARKET, 1);
        orderComparator = Comparator.comparing(Order::getOrderType,
                Comparator.comparing(t -> priority.getOrDefault(t, 0)))
                .thenComparing(Order::getPrice, Comparator.reverseOrder())
                .thenComparing(Order::getDatetime)
                .thenComparing(Order::getOrderId);
    }
}
