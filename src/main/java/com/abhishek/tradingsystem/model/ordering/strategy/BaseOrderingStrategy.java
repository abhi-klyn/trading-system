package com.abhishek.tradingsystem.model.ordering.strategy;

import com.abhishek.tradingsystem.model.Order;

import java.util.Comparator;

/**
 * Base Ordering Strategy that Operations can use to define ordering of their choice.
 */
public abstract class BaseOrderingStrategy {
    Comparator<Order> orderComparator;

    /**
     * Default Ordering, based on order-id.
     */
    public BaseOrderingStrategy() {
        orderComparator = Comparator.comparing(Order::getOrderId);
    }

    /**
     * Returns a comparator that was defined using this class.
     * @return
     */
    public Comparator<Order> getComparator(){
        return orderComparator;
    }
}
