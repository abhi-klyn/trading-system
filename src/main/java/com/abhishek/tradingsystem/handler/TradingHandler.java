package com.abhishek.tradingsystem.handler;

import com.abhishek.tradingsystem.model.Order;
import com.abhishek.tradingsystem.model.TradingService;
import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import com.abhishek.tradingsystem.model.ordering.strategy.DecreasingPriceStrategy;
import com.abhishek.tradingsystem.model.ordering.strategy.IncreasingPriceStrategy;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Handler that is used to call driver methods for the TradingService.
 */
public class TradingHandler {
    private static final TradingService tradingService = new TradingService();

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
    public static String addOrder(BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                                  @NonNull Operation operation, OrderType orderType) {
        return tradingService.addOrder(price, symbol, quantity, datetime, operation, orderType);
    }

    /**
     * Adds new Order to the System and returns the orderId.
     *
     * @param order
     * @return String, orderId
     */
    public static String addOrder(Order order) {
        return tradingService.addOrder(order);
    }

    /**
     * Removes the existing order from the System, returns boolean if the order was successfully removed.
     *
     * @param orderId
     * @return
     */
    public static boolean removeOrder(final String orderId) {
        return tradingService.removeOrder(orderId);
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
    public static boolean updateOrder(String orderId, BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                                      Operation operation, OrderType orderType) {
        return tradingService.updateOrder(orderId, price, symbol, quantity, datetime, operation, orderType);
    }

    /**
     * Access the order in the database, based on Order-id.
     *
     * @param orderId
     * @return Order object
     */
    public static Order accessOrder(final String orderId) {
        return tradingService.accessOrder(orderId);
    }

    /**
     * Lists the orders according to the comparison order mentioned for each operation.
     * For ordering information check {@link IncreasingPriceStrategy} or {@link DecreasingPriceStrategy}
     *
     * @param operation (BUY/SELL)
     * @return List<Order>
     */
    public static List<Order> listOrders(final Operation operation) {
        return tradingService.listOrders(operation);
    }
}