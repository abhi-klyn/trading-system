package com.abhishek.tradingsystem.controllers;

import com.abhishek.tradingsystem.handler.TradingHandler;
import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TradingController {

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public ResponseEntity addOrder(@NonNull final BigDecimal price,
                                   final Symbol symbol,
                                   final int quantity,
                                   @NonNull final LocalDateTime datetime,
                                   @NonNull final Operation operation,
                                   final OrderType orderType) {
        String orderId = TradingHandler.addOrder(price, symbol, quantity, datetime, operation, orderType);
        return ResponseEntity.ok(orderId);
    }

    @RequestMapping(value = "/order/remove", method = RequestMethod.POST)
    public ResponseEntity removeOrder(@NonNull final String orderId) {
        TradingHandler.removeOrder(orderId);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/order/accessOrder", method = RequestMethod.GET)
    public ResponseEntity accessOrder(@NonNull final String orderId) {
        Order order = TradingHandler.accessOrder(orderId);
        return ResponseEntity.ok(order.toString());
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public ResponseEntity listOrder(@NonNull final Operation operation) {
        List<Order> orderList = TradingHandler.listOrders(operation);
        return ResponseEntity.ok(orderList.toString());
    }


}
