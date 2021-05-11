package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.ordering.strategy.BaseOrderingStrategy;

import java.util.Collection;
import java.util.Iterator;

public abstract class BaseOrderOperation {
    protected BaseOrderingStrategy baseOrderingStrategy;
    protected Collection<Order> data;

    public BaseOrderOperation(final BaseOrderingStrategy baseOrderingStrategy) {
        this.baseOrderingStrategy = baseOrderingStrategy;
    }

    public boolean addOrder(final Order order) {
        data.add(order);
        return true;
    }

    public boolean removeOrder(final Order order) {
        if (data.contains(order)) {
            data.remove(order);
            return true;
        }
        return false;
    }

    public abstract boolean updateOrder(final Order oldOrder, final Order newOrder);

    public Iterator<Order> listOrders() {
        return data.iterator();
    }
}
