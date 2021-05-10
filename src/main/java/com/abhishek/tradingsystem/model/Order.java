package com.abhishek.tradingsystem.model;

import com.abhishek.tradingsystem.model.enums.Symbol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class Order {
    private String orderId;
    @Setter private BigDecimal price;
    @Setter private Symbol symbol;
    @Setter private int quantity;
    @Setter private LocalDateTime datetime;

}
