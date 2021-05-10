package com.abhishek.tradingsystem.model;

import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.order.operations.BaseOrderOperation;
import com.abhishek.tradingsystem.model.order.operations.BuyOrders;
import com.abhishek.tradingsystem.model.order.operations.SellOrders;
import com.abhishek.tradingsystem.model.ordering.stratergy.DescPriceIncTime;
import com.abhishek.tradingsystem.model.ordering.stratergy.IncPriceIncTime;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TradingService {
    private final Map<Operation, BaseOrderOperation> operations;
    private final Map<String, Order> orderData;

    public TradingService() {
        operations = new HashMap<>();
        orderData = new HashMap<>();
        operations.put(BuyOrders.OPERATION_TYPE, new BuyOrders(this, new DescPriceIncTime()));
        operations.put(SellOrders.OPERATION_TYPE, new SellOrders(this, new IncPriceIncTime()));
    }

    public boolean addOrder(final Order order) {
        orderData.put(order.getOrderId(), order);
        return operations.get(order.getOperation()).addOrder(order);
    }

    public boolean removeOrder(final Order order) {
        orderData.remove(order.getOrderId());
        return operations.get(order.getOperation()).removeOrder(order);
    }

    public Order accessOrder(final String orderId) {
        return orderData.get(orderId);
    }

    public List<String> listOrders(Operation operation){
        Iterator<Order> orderIterator = operations.get(operation).listOrders();
        Iterable<Order> iterableOrder = () -> orderIterator;
        return StreamSupport.stream(iterableOrder.spliterator(), false)
                .map(e -> e.toString())
                .collect(Collectors.toList());
    }
}
