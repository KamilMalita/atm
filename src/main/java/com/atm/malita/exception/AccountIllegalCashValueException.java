package com.atm.malita.exception;

import java.math.BigDecimal;

public class AccountIllegalCashValueException extends RuntimeException{
    public AccountIllegalCashValueException(BigDecimal value) {
        super("Provided cash must be non-negative, actual: "+ value);
    }
}
