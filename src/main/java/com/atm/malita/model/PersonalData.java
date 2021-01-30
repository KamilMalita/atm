package com.atm.malita.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalData {
    private String name;
    private String surname;

    private PersonalData(){}

    public PersonalData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
