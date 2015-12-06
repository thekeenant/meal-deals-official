package com.keenant.mealdeals.data;

import java.util.Date;

import lombok.Getter;

public class User {
    @Getter final int id;
    @Getter final String email;
    @Getter final Date dob;

    public User(int id, String email, Date dob) {
        this.id = id;
        this.email = email;
        this.dob = dob;
    }
}
