package com.atm.malita.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Operation {
    DEPOSIT {
        @Override
        public void doTransactionOperation(Account account, BigDecimal cash) {
            account.getAmount().accumulateAndGet(cash,
                    (num1, num2) -> num1.add(num2).setScale(2, RoundingMode.HALF_EVEN));
        }
    },
    WITHDRAW {
        @Override
        public void doTransactionOperation(Account account, BigDecimal cash) {
            account.getAmount().accumulateAndGet(cash,
                    (num1, num2) -> num1.subtract(num2).setScale(2, RoundingMode.HALF_EVEN));
        }
    };

    public abstract void doTransactionOperation(Account account, BigDecimal cash);
}
