package com.atm.malita.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class Account {
    private final static BigDecimal START_MONEY = new BigDecimal(0);

    private final String uniqueId;
    private final PersonalData personalData;
    private final AtomicReference<BigDecimal> amount;
    private final ConcurrentLinkedQueue<History> historyOperation;

    public Account(PersonalData personalData) {
        this.uniqueId = UUID.randomUUID().toString();
        this.personalData = personalData;
        this.amount = new AtomicReference<>(START_MONEY);
        this.historyOperation = new ConcurrentLinkedQueue<>();
    }
}
