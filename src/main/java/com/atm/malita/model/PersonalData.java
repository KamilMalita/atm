package com.atm.malita.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonalData {
    @NotNull
    @NotBlank
    @Length(min = 5, message = "Name must be longer than 5")
    private String name;
    @NotNull
    @NotBlank
    @Length(min = 5, message = "Surname must be longer than 5")
    private String surname;

    private PersonalData(){
        throw new AssertionError();
    }

    public PersonalData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
