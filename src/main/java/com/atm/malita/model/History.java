package com.atm.malita.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public final class History {
    private final Operation operation;
    private final BigDecimal amount;
    private final LocalDateTime operationDate;


    public History(Operation operation, BigDecimal amount, LocalDateTime operationDate) {
        this.operation = operation;
        this.amount = amount;
        this.operationDate = operationDate;
    }
}
