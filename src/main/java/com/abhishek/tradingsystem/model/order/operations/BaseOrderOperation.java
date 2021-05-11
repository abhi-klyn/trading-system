package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.ordering.strategy.BaseOrderingStrategy;

import java.util.Collection;
import java.util.Iterator;

/**
 * Base abstract class for the Order Operation.
 * It is responsible for maintaining the given order data in specified order.
 * <p>
 * It has add/ remove update functions that help the Operations data to be in sync with the Main data in TradingService.
 * <p>
 * Each Operation is decoupled, hence can use their Collection type of their choice. They can also implement add, remove, update
 * as they prefer
 */
public abstract class BaseOrderOperation {
    protected BaseOrderingStrategy baseOrderingStrategy;
    protected Collection<Order> data;

    /**
     * An ordering strategy for the orders can be chosen. Which is decoupled and is extensible as well.
     *
     * @param baseOrderingStrategy
     */
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

    /**
     * Since there is no default update for all Collections, we leave that to be implemented by Children
     *
     * @param oldOrder
     * @param newOrder
     * @return
     */
    public abstract boolean updateOrder(final Order oldOrder, final Order newOrder);

    /**
     * Returns the iterator for the data, which is expected to be in the specified order.
     *
     * @return
     */
    public Iterator<Order> listOrders() {
        return data.iterator();
    }
}
