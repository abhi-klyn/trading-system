package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.exceptions.InvalidUpdateException;
import com.abhishek.tradingsystem.exceptions.OrderNotFoundException;
import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.strategy.BaseOrderingStrategy;
import com.abhishek.tradingsystem.model.ordering.strategy.IncreasingPriceStrategy;

import java.util.TreeSet;

/**
 * To Save the ordering format for the orders using {@link IncreasingPriceStrategy}
 * Uses a Treeset along with the above custom comparator to save the data.
 */
public class SellOrders extends BaseOrderOperation {
    public static Operation OPERATION_TYPE = Operation.SELL;

    public SellOrders(BaseOrderingStrategy baseOrderingStrategy) {
        super(baseOrderingStrategy);
        this.data = new TreeSet<>(baseOrderingStrategy.getComparator());
    }

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
