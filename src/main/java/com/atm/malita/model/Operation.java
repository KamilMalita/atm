package com.atm.malita.model;

import java.math.BigDecimal;

public enum Operation {
    DEPOSIT {
        @Override
        public void doTransactionOperation(Account account, BigDecimal cash) {
            account.deposeMoney(cash);
        }
    },
    WITHDRAW {
        @Override
        public void doTransactionOperation(Account account, BigDecimal cash) {
            account.withdrawMoney(cash);
        }
    };

    public abstract void doTransactionOperation(Account account, BigDecimal cash);
}
