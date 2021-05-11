package com.abhishek.tradingsystem.utils;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class Utils {
    public static Order generateRandomOrder(BigDecimal price, Symbol symbol, LocalDateTime datetime,
                                            Operation operation, OrderType orderType) {
        Random random = new Random();
        Order newOrder = new Order(
                (price == null) ? new BigDecimal(random.nextInt()) : price,
                (symbol == null) ? Symbol.AAPL : symbol,
                random.nextInt(),
                (datetime == null) ? LocalDateTime.now() : datetime,
                (operation == null) ? Operation.BUY : operation,
                (orderType == null) ? OrderType.MARKET : orderType
        );
        return newOrder;
    }

    public static Order generateRandomOrder() {
        return generateRandomOrder(null, null, null, null, null);
    }
}
