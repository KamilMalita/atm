package com.atm.malita.model;

import com.atm.malita.exception.AccountIllegalCashValueException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Account {
    private final static BigDecimal START_MONEY = new BigDecimal(0);

    private final String uniqueId;
    private final PersonalData personalData;
    private final AtomicReference<BigDecimal> amount;
    private final ConcurrentLinkedQueue<History> historyOperation;

    private Account(){
        throw new AssertionError();
    }

    public Account(PersonalData personalData) {
        this.uniqueId = UUID.randomUUID().toString();
        this.personalData = personalData;
        this.amount = new AtomicReference<>(START_MONEY);
        this.historyOperation = new ConcurrentLinkedQueue<>();
    }

    public void deposeMoney(BigDecimal cash) {
        if (cash.compareTo(BigDecimal.ZERO) <= 0)
            throw new AccountIllegalCashValueException(cash);
        amount.accumulateAndGet(cash,
                (num1, num2) -> num1.add(num2).setScale(2, RoundingMode.DOWN));
        addHistoryRecord(Operation.DEPOSIT, cash);
    }

    public void withdrawMoney(BigDecimal cash) {
        if (cash.compareTo(BigDecimal.ZERO) <= 0)
            throw new AccountIllegalCashValueException(cash);
        amount.accumulateAndGet(cash,
                (num1, num2) -> num1.subtract(num2).setScale(2, RoundingMode.DOWN));
        addHistoryRecord(Operation.WITHDRAW, cash);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public BigDecimal getAmount() {
        return amount.get();
    }

    public Collection<History> getHistoryOperation() {
        return Collections.unmodifiableCollection(historyOperation);
    }

    private void addHistoryRecord(Operation operation, BigDecimal cash) {
        historyOperation.add(new History(operation, cash, LocalDateTime.now()));
    }

}
