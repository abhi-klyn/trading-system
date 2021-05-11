package com.abhishek.tradingsystem.model;

import com.abhishek.tradingsystem.model.enums.Operation;
import com.abhishek.tradingsystem.model.enums.OrderType;
import com.abhishek.tradingsystem.model.enums.Symbol;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * POJO object for order
 */
@ToString
@Getter
public class Order {
    @NonNull
    private final Operation operation;
    @NonNull
    private String orderId;
    @Setter
    private BigDecimal price;
    @Setter
    private Symbol symbol;
    @Setter
    private int quantity;
    @Setter
    private LocalDateTime datetime;
    @Setter
    private OrderType orderType;

    public Order(@NonNull BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                 @NonNull Operation operation, OrderType orderType) {
        // todo: Id generation service might be needed to avoid collisions.
        this.orderId = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        this.price = price;
        this.symbol = symbol;
        this.quantity = quantity;
        this.datetime = datetime;
        this.orderType = orderType;
        this.operation = operation;
    }

    public Order(@NonNull String orderId, @NonNull BigDecimal price, Symbol symbol, int quantity, LocalDateTime datetime,
                 @NonNull Operation operation, OrderType orderType) {
        this(price, symbol, quantity, datetime, operation, orderType);
        this.orderId = orderId;
    }
}
