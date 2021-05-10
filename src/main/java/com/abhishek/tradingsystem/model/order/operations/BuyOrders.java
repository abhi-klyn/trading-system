package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.ordering.stratergy.DescPriceIncTime;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BuyOrders implements BaseOrderOperation {
    Set<Order> set = new TreeSet<>(new DescPriceIncTime());

    @Override
    public String addOrder(Order order) {
        return null;
    }

    @Override
    public String removeOrder(String orderId) {
        return null;
    }

    @Override
    public Order accessOrder(String orderId) {
        return null;
    }

    @Override
    public Iterator<Order> listOrders() {
        return null;
    }
}
