package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.exceptions.InvalidUpdateException;
import com.abhishek.tradingsystem.exceptions.OrderNotFoundException;
import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.stratergy.BaseOrderingStrategy;

import java.util.TreeSet;

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
