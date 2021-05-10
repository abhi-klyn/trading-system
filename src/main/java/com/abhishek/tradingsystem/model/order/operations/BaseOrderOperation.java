package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.Order;

import java.util.Iterator;

public interface BaseOrderOperation {
    public String addOrder(final Order order);

    public String removeOrder(final String orderId);

    public Order accessOrder(final String orderId);

    public Iterator<Order> listOrders();
}
