package com.abhishek.tradingsystem.model;

import com.abhishek.tradingsystem.exceptions.OrderNotFoundException;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import com.abhishek.tradingsystem.model.order.operations.BaseOrderOperation;
import com.abhishek.tradingsystem.model.order.operations.BuyOrders;
import com.abhishek.tradingsystem.model.order.operations.SellOrders;
import com.abhishek.tradingsystem.model.ordering.strategy.DecreasingPriceStrategy;
import com.abhishek.tradingsystem.model.ordering.strategy.IncreasingPriceStrategy;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service and in-memory database for the various operations of the Trading System
 */
public class TradingService {
    private final Map<Operation, BaseOrderOperation> operations;
    private final Map<String, Order> orderData;

    public TradingService() {
        operations = new HashMap<>();
        orderData = new HashMap<>();
        operations.put(BuyOrders.OPERATION_TYPE, new BuyOrders(new DecreasingPriceStrategy()));
        operations.put(SellOrders.OPERATION_TYPE, new SellOrders(new IncreasingPriceStrategy()));
    }

    /**
     * Adds new Order to the System and returns the orderId.
     * Also, attempts to add the order to the respective Operation (Buy/Sell Handlers)
     *
     * @param order
     * @return String
     */
    public String addOrder(final Order order) {
        orderData.put(order.getOrderId(), order);
        operations.get(order.getOperation()).addOrder(order);
        return order.getOrderId();
    }

    /**
     * Adds new Order to System, when parameters are passed.
     *
     * @param price
     * @param symbol
     * @param quantity
     * @param datetime
     * @param operation
     * @param orderType
     * @return
     */
    public String addOrder(BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                           @NonNull Operation operation, OrderType orderType) {
        Order newOrder = new Order(price, symbol, quantity, datetime, operation, orderType);
        return addOrder(newOrder);
    }

    /**
     * Removes the existing order from the System, returns boolean if the order was successfully removed.
     * Also attempts to remove the order from the respective operations.
     *
     * @param order
     * @return
     */
    public boolean removeOrder(final Order order) {
        if (!orderData.containsKey(order.getOrderId())) {
            throw new OrderNotFoundException();
        }
        orderData.remove(order.getOrderId());
        return operations.get(order.getOperation()).removeOrder(order);
    }

    /**
     * Similar to above, but removes Order based on order-id, instead of object.
     *
     * @param orderId
     * @return
     */
    public boolean removeOrder(final String orderId) {
        return removeOrder(orderData.get(orderId));
    }

    /**
     * Access the order in the database, based on Order-id.
     *
     * @param orderId
     * @return
     */
    public Order accessOrder(final String orderId) {
        return orderData.get(orderId);
    }

    /**
     * Given the existing orderId any of the other params can be updated, the update is propagated through operations data.
     *
     * @param orderId
     * @param price
     * @param symbol
     * @param quantity
     * @param datetime
     * @param operation
     * @param orderType
     * @return
     */
    public boolean updateOrder(final String orderId, BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                               @NonNull Operation operation, OrderType orderType) {
        if (!orderData.containsKey(orderId)) {
            throw new OrderNotFoundException();
        }
        Order oldOrder = orderData.get(orderId);
        Order newOrder = new Order(orderId, price, symbol, quantity, datetime, operation, orderType);
        orderData.put(newOrder.getOrderId(), newOrder);
        return operations.get(newOrder.getOperation()).updateOrder(oldOrder, newOrder);
    }

    /**
     * Lists the orders according to the comparison order mentioned for each operation.
     *
     * @param operation
     * @return
     */
    public List<Order> listOrders(Operation operation) {
        Iterator<Order> orderIterator = operations.get(operation).listOrders();
        Iterable<Order> iterableOrder = () -> orderIterator;
        return StreamSupport.stream(iterableOrder.spliterator(), false)
                .collect(Collectors.toList());
    }
}
