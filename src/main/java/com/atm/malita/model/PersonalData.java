package com.atm.malita.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonalData {
    @NotBlank
    @Length(min = 5, message = "Name must be longer than 5")
    private String name;
    @Length(min = 5, message = "Surname must be longer than 5")
    private String surname;

    private PersonalData(){}

    public PersonalData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
