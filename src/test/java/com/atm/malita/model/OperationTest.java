package com.atm.malita.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

class OperationTest {

    @Test
    void doTransactionOperation() {
        Account acc1 = new Account(new PersonalData("Name", "Surname"));

        Operation.DEPOSIT.doTransactionOperation(acc1, new BigDecimal(100.15));
        Assertions.assertEquals(new BigDecimal("100.15"), acc1.getAmount().get());

        Operation.WITHDRAW.doTransactionOperation(acc1, new BigDecimal(100.33));
        Assertions.assertEquals(new BigDecimal("-0.18"), acc1.getAmount().get());
    }
}