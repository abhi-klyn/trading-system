package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.exceptions.InvalidUpdateException;
import com.abhishek.tradingsystem.exceptions.OrderNotFoundException;
import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.strategy.BaseOrderingStrategy;
import com.abhishek.tradingsystem.model.ordering.strategy.DecreasingPriceStrategy;

import java.util.TreeSet;

/**
 * To Save the ordering format for the orders using {@link DecreasingPriceStrategy}
 * Uses a Treeset along with the above custom comparator to save the data.
 */
public class BuyOrders extends BaseOrderOperation {
    public static Operation OPERATION_TYPE = Operation.BUY;

    public BuyOrders(final BaseOrderingStrategy baseOrderingStrategy) {
        super(baseOrderingStrategy);
        this.data = new TreeSet<>(baseOrderingStrategy.getComparator());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateOrder(Order oldOrder, Order newOrder) {
        if (!oldOrder.getOrderId().equals(newOrder.getOrderId())) {
            throw new InvalidUpdateException();
        }

        if (!data.contains(oldOrder)) {
            throw new OrderNotFoundException();
        }

        data.remove(oldOrder);
        data.add(newOrder);
        return true;
    }
}
