package com.abhishek.tradingsystem.controllers;

import com.abhishek.tradingsystem.handler.TradingHandler;
import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import com.abhishek.tradingsystem.model.ordering.strategy.DecreasingPriceStrategy;
import com.abhishek.tradingsystem.model.ordering.strategy.IncreasingPriceStrategy;
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

    /**
     * Adds new Order using mentioned to the System and returns the orderId.
     *
     * @param price
     * @param symbol
     * @param quantity
     * @param datetime
     * @param operation
     * @param orderType
     * @return String
     */
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

    /**
     * Removes the existing order from the System, returns boolean if the order was successfully removed.
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/remove", method = RequestMethod.POST)
    public ResponseEntity removeOrder(@NonNull final String orderId) {
        TradingHandler.removeOrder(orderId);
        return ResponseEntity.ok("");
    }

    /**
     * Given the existing orderId. params can be updated.
     *
     * @param orderId
     * @param price
     * @param symbol
     * @param quantity
     * @param datetime
     * @param operation
     * @param orderType
     * @return boolen, update successful
     */
    @RequestMapping(value = "/order/update", method = RequestMethod.POST)
    public ResponseEntity updateOrder(@NonNull final String orderId,
                                      final BigDecimal price,
                                      final Symbol symbol,
                                      final int quantity,
                                      final LocalDateTime datetime,
                                      final Operation operation,
                                      final OrderType orderType) {
        TradingHandler.updateOrder(orderId, price, symbol, quantity, datetime, operation, orderType);
        return ResponseEntity.ok("");
    }

    /**
     * Access the order in the database, based on Order-id.
     *
     * @param orderId
     * @return Order object
     */
    @RequestMapping(value = "/order/accessOrder", method = RequestMethod.GET)
    public ResponseEntity accessOrder(@NonNull final String orderId) {
        Order order = TradingHandler.accessOrder(orderId);
        return ResponseEntity.ok(order.toString());
    }

    /**
     * Lists the orders according to the comparison order mentioned for each operation.
     * For ordering information check {@link IncreasingPriceStrategy} or {@link DecreasingPriceStrategy}
     *
     * @param operation (BUY/SELL)
     * @return List<Order>
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public ResponseEntity listOrder(@NonNull final Operation operation) {
        List<Order> orderList = TradingHandler.listOrders(operation);
        return ResponseEntity.ok(orderList.toString());
    }


}
