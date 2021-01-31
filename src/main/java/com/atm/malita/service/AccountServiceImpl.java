package com.atm.malita.service;

import com.atm.malita.model.Account;
import com.atm.malita.model.Operation;
import com.atm.malita.model.PersonalData;
import com.atm.malita.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public BigDecimal getBalance(String accountId) {
        Optional<Account> accountOptional = AccountRepository.findAccountById(accountId);
        //TODO Exception handler
        Account account = accountOptional.orElseThrow(null);
        return account.getAmount();
    }

    @Override
    public String createAccount(String name, String surname) {
        Account account = new Account(new PersonalData(name, surname));
        AccountRepository.addAccount(account);
        return account.getUniqueId();
    }

    @Override
    public void businessOperation(Operation operation, String accountId, BigDecimal cash) {
        Optional<Account> accountOptional = AccountRepository.findAccountById(accountId);
        //TODO Exception handler
        Account account = accountOptional.orElseThrow(null);
        operation.doTransactionOperation(account, cash);
    }


}
