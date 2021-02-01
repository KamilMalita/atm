package com.atm.malita.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public final class BusinessObject {
    private Operation operation;
    private BigDecimal amount;
    private String accountId;

}
