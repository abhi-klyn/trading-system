package com.abhishek.tradingsystem.handler;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.TradingService;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TradingHandler {
    private static TradingService tradingService = new TradingService();

    public static String addOrder(BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                                  @NonNull Operation operation, OrderType orderType)  {
        return tradingService.addOrder(price, symbol, quantity, datetime, operation, orderType);
    }

    public static boolean removeOrder(final String orderId)  {
        return tradingService.removeOrder(orderId);
    }

    public static Order accessOrder(final String orderId) {
        return tradingService.accessOrder(orderId);
    }

    public static List<Order> listOrders(final Operation operation) {
        return tradingService.listOrders(operation);
    }
}