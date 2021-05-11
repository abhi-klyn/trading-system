package com.abhishek.tradingsystem.model;

import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import com.abhishek.tradingsystem.model.order.operations.BaseOrderOperation;
import com.abhishek.tradingsystem.model.order.operations.BuyOrders;
import com.abhishek.tradingsystem.model.order.operations.SellOrders;
import com.abhishek.tradingsystem.model.ordering.stratergy.DecreasingPriceStrategy;
import com.abhishek.tradingsystem.model.ordering.stratergy.IncreasingPriceStrategy;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TradingService {
    private final Map<Operation, BaseOrderOperation> operations;
    private final Map<String, Order> orderData;

    public TradingService() {
        operations = new HashMap<>();
        orderData = new HashMap<>();
        operations.put(BuyOrders.OPERATION_TYPE, new BuyOrders(new DecreasingPriceStrategy()));
        operations.put(SellOrders.OPERATION_TYPE, new SellOrders(new IncreasingPriceStrategy()));
    }

    public String addOrder(final Order order) {
        orderData.put(order.getOrderId(), order);
        operations.get(order.getOperation()).addOrder(order);
        return order.getOrderId();
    }

    public String addOrder(BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                            @NonNull Operation operation, OrderType orderType) {
        Order newOrder = new Order(price, symbol, quantity, datetime, operation, orderType);
        return addOrder(newOrder);
    }

    public boolean removeOrder(final Order order) {
        orderData.remove(order.getOrderId());
        return operations.get(order.getOperation()).removeOrder(order);
    }

    public boolean removeOrder(final String orderId) {
        return removeOrder(orderData.get(orderId));
    }

    public Order accessOrder(final String orderId) {
        return orderData.get(orderId);
    }

    public List<Order> listOrders(Operation operation) {
        Iterator<Order> orderIterator = operations.get(operation).listOrders();
        Iterable<Order> iterableOrder = () -> orderIterator;
        return StreamSupport.stream(iterableOrder.spliterator(), false)
                .collect(Collectors.toList());
    }
}
