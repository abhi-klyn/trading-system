package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.TradingService;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.stratergy.OrderingStratergy;

import java.util.TreeSet;

public class BuyOrders extends BaseOrderOperation {
    public static Operation OPERATION_TYPE = Operation.BUY;

    public BuyOrders(final OrderingStratergy orderingStratergy) {
        super(orderingStratergy);
        this.data = new TreeSet<>(orderingStratergy);
    }
}
