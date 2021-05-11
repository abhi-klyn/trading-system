package com.abhishek.tradingsystem.model.order.operations;

import com.abhishek.tradingsystem.model.TradingService;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.ordering.stratergy.OrderingStratergy;

import java.util.TreeSet;

public class SellOrders extends BaseOrderOperation {
    public static Operation OPERATION_TYPE = Operation.SELL;

    public SellOrders(OrderingStratergy orderingStratergy) {
        super(orderingStratergy);
        this.data = new TreeSet<>(orderingStratergy.getComparator());
    }
}
