package com.atm.malita.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
}