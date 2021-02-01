package com.atm.malita.service;

import com.atm.malita.exception.AccountNotFoundException;
import com.atm.malita.model.Account;
import com.atm.malita.model.BusinessObject;
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
        Account account = accountOptional.orElseThrow(() -> new AccountNotFoundException(accountId));
        return account.getAmount();
    }

    @Override
    public String createAccount(PersonalData personalData) {
        Account account = new Account(personalData);
        AccountRepository.addAccount(account);
        return account.getUniqueId();
    }

    @Override
    public void businessOperation(BusinessObject businessObject) {
        Optional<Account> accountOptional = AccountRepository.findAccountById(businessObject.getAccountId());
        Account account = accountOptional.orElseThrow(() -> new AccountNotFoundException(businessObject.getAccountId()));
        businessObject.getOperation().doTransactionOperation(account, businessObject.getAmount());
    }


}
