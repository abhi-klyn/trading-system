package com.abhishek.tradingsystem.commands;

import com.abhishek.tradingsystem.model.TradingService;

public class CommandExecutor {
    protected TradingService tradingService;

    public CommandExecutor(final TradingService tradingService) {
        this.tradingService = tradingService;
    }

    public abstract void execute(Command command);
}
