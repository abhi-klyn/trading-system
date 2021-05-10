package com.abhishek.tradingsystem.model.ordering.stratergy;

import com.abhishek.tradingsystem.model.Order;

public class DescPriceIncTime extends OrderingStratergy {
    @Override
    public int compare(Order o1, Order o2) {
        int priceCompare = o2.getPrice().compareTo(o1.getPrice());
        if (priceCompare != 0) {
            return priceCompare;
        }
        int timeCompare = o1.getDatetime().compareTo(o2.getDatetime());
        if (timeCompare != 0) {
            return timeCompare;
        }
        return o1.getOrderId().compareTo(o2.getOrderId());
    }
}
