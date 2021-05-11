package com.abhishek.tradingsystem.model.ordering.stratergy;

import com.abhishek.tradingsystem.model.Order;

import java.util.Comparator;

public abstract class OrderingStratergy {
    Comparator<Order> orderComparator;

    public OrderingStratergy() {
        orderComparator = Comparator.comparing(Order::getOrderId);
    }

    public Comparator<Order> getComparator(){
        return orderComparator;
    }
}
