package com.keenant.mealdeals.data;

import lombok.Getter;

public class Transaction {
    @Getter final int user;
    @Getter final int deal;
    @Getter final int points;

    public Transaction(int user, int deal, int points) {
        this.user = user;
        this.deal = deal;
        this.points = points;
    }
}
