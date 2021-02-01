package com.atm.malita.service;

import com.atm.malita.model.BusinessObject;
import com.atm.malita.model.Operation;
import com.atm.malita.model.PersonalData;

import java.math.BigDecimal;

public interface AccountService {
    BigDecimal getBalance(String accountId);

    String createAccount(PersonalData personalData);

    void businessOperation(BusinessObject businessObject);
}
