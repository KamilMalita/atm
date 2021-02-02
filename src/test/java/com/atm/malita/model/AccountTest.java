package com.atm.malita.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;

    @BeforeEach
    void createNewAccount() {
        account = new Account(new PersonalData("", ""));
    }

    @Test
    void deposeMoney() {
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                account.deposeMoney(new BigDecimal("1"));
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(numberOfThreads + ".00", account.getAmount().toString());
        assertEquals(numberOfThreads, account.getHistoryOperation().size());
    }

    @Test
    void withdrawMoney() {
        int numberOfThreads = 8;
        ExecutorService service = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                account.withdrawMoney(new BigDecimal("1"));
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("-" + numberOfThreads + ".00", account.getAmount().toString());
        assertEquals(numberOfThreads, account.getHistoryOperation().size());
    }

    @Test
    void testModel() {
        assertNotNull(account.getUniqueId());
        assertNotNull(account.getHistoryOperation());
        assertNotNull(account.getAmount());
        assertNotNull(account.getPersonalData());
        assertEquals(0, account.getHistoryOperation().size());
        // check if uniqueId is in UUID format
        boolean matchId = account.getUniqueId().matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
        assertTrue(matchId);
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.class, account.getAmount().getClass());
    }

    @Test
    void uniqueObject() {
        Account account1 = new Account(new PersonalData("xxxxxxxxxx", "xxxxxxxxxx"));
        Account account2 = new Account(new PersonalData("xxxxxxxxxx", "xxxxxxxxxx"));
        assertNotEquals(account1.getUniqueId(), account2.getUniqueId());
    }
}