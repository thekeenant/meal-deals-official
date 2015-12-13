package com.keenant.mealdeals.data;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter final int id;
    @Getter final String email;
    @Getter final Date dob;
    @Getter List<Transaction> transactions;

    public User(int id, String email, Date dob, List<Transaction> transactions) {
        this.id = id;
        this.email = email;
        this.dob = dob;
        this.transactions = transactions;
    }

    public int getPoints() {
        int i = 0;
        for (Transaction t : transactions)
            i += t.getPoints();
        return i;
    }
}
