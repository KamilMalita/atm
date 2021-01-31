package com.atm.malita.repository;

import com.atm.malita.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {
    private final static ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public static Optional<Account> findAccountById(String accountId) {
        return Optional.ofNullable(accounts.getOrDefault(accountId, null));
    }

    public static Account addAccount(Account account) {
        return accounts.putIfAbsent(account.getUniqueId(), account);
    }

    private AccountRepository() {
    }
}
