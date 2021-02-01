package com.atm.malita.validation;

import com.atm.malita.model.BusinessObject;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

public class BusinessObjectValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return BusinessObject.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountId", "accountId.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "operation", "operation.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "amount.required");
        BusinessObject businessObject = (BusinessObject) o;
        if (businessObject.getAmount().compareTo(BigDecimal.ZERO)<=0)
            errors.rejectValue("amount","Amount must be grater than 0!");
    }
}
