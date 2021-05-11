package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.stratergy.BaseOrderingStrategy;

import java.util.TreeSet;

public class BuyOrders extends BaseOrderOperation {
    public static Operation OPERATION_TYPE = Operation.BUY;

    public BuyOrders(final BaseOrderingStrategy baseOrderingStrategy) {
        super(baseOrderingStrategy);
        this.data = new TreeSet<>(baseOrderingStrategy.getComparator());
    }
}
