package com.atm.malita.service;

import com.atm.malita.model.Account;
import com.atm.malita.model.BusinessObject;
import com.atm.malita.model.Operation;
import com.atm.malita.model.PersonalData;
import com.atm.malita.repository.AccountRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    PersonalData personalData;

    @Test
    void createAccount() {
        AccountServiceImpl accountService = new AccountServiceImpl();
        String uniqueId = accountService.createAccount(personalData);
        assertNotNull(uniqueId);
    }

    @Test
    void businessOperation() {
        AccountServiceImpl accountService = new AccountServiceImpl();
        String uniqeId = accountService.createAccount(new PersonalData("xxxxxxxx", "xxxxxxx"));
        BigDecimal startAmount = accountService.getBalance(uniqeId);
        BigDecimal endAmount = new BigDecimal("10");


        BusinessObject businessObject = new BusinessObject();
        businessObject.setOperation(Operation.DEPOSIT);
        businessObject.setAmount(new BigDecimal("10"));
        businessObject.setAccountId(uniqeId);


        accountService.businessOperation(businessObject);
        BigDecimal amount = accountService.getBalance(uniqeId);

        assertEquals(0, endAmount.compareTo(amount.subtract(startAmount)));

    }
}