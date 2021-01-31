package com.atm.malita.repository;

import com.atm.malita.model.Account;
import com.atm.malita.model.PersonalData;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    @Test()
    void accountRepositoryTest() {
        Account account = new Account(new PersonalData("", ""));
        AccountRepository.addAccount(account);

        assertTrue(AccountRepository.findAccountById(account.getUniqueId()).isPresent());
        assertFalse(AccountRepository.findAccountById(UUID.randomUUID().toString()).isPresent());
    }
}