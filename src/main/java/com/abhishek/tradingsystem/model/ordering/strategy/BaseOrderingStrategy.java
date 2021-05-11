package com.abhishek.tradingsystem.model.ordering.strategy;

import com.abhishek.tradingsystem.model.Order;

import java.util.Comparator;

/**
 * Base Ordering Strategy that Operations can use to define ordering of their choice.
 */
public interface BaseOrderingStrategy {

    /**
     * Returns a comparator that was defined using this class.
     *
     * @return
     */
    Comparator<Order> getComparator();
}
