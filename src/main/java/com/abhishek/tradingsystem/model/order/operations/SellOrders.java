package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.stratergy.BaseOrderingStrategy;

import java.util.TreeSet;

public class SellOrders extends BaseOrderOperation {
    public static Operation OPERATION_TYPE = Operation.SELL;

    public SellOrders(BaseOrderingStrategy baseOrderingStrategy) {
        super(baseOrderingStrategy);
        this.data = new TreeSet<>(baseOrderingStrategy.getComparator());
    }
}
