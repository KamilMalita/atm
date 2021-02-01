package com.atm.malita.controller;

import com.atm.malita.model.BusinessObject;
import com.atm.malita.model.Operation;
import com.atm.malita.model.PersonalData;
import com.atm.malita.service.AccountService;
import com.atm.malita.service.AccountServiceImpl;
import com.atm.malita.validation.BusinessObjectValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @InitBinder("businessObject")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new BusinessObjectValidation());
    }

    @GetMapping("/accountBalance")
    public Map<String, BigDecimal> getAccountBalance(@RequestParam String accountId) {
        BigDecimal amount = accountService.getBalance(accountId);
        return Collections.singletonMap("amount", amount);
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> addAccount(@RequestBody @Validated PersonalData personalData) {
        String accountId = accountService.createAccount(personalData);
        return Collections.singletonMap("uniqueId", accountId);
    }

    @PostMapping("/businessOperation")
    public Map<String, String> businessOperation(@RequestBody @Valid BusinessObject businessObject) {
        accountService.businessOperation(businessObject);
        return Collections.singletonMap("uniqueId", businessObject.getAccountId());
    }

}
