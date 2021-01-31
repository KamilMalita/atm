package com.atm.malita.service;

import com.atm.malita.model.Operation;

import java.math.BigDecimal;

public interface AccountService {
    BigDecimal getBalance(String accountId);

    String createAccount(String name, String surname);

    void businessOperation(Operation operation, String accountId, BigDecimal cash);
}
