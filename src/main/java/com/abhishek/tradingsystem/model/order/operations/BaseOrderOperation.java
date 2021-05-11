package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.ordering.stratergy.OrderingStratergy;

import java.util.Collection;
import java.util.Iterator;

public abstract class BaseOrderOperation {
    protected OrderingStratergy orderingStratergy;
    protected Collection<Order> data;

    public BaseOrderOperation(final OrderingStratergy orderingStratergy) {
        this.orderingStratergy = orderingStratergy;
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

    public Iterator<Order> listOrders() {
        return data.iterator();
    }
}
