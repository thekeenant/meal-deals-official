package com.keenant.mealdeals.data;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Mall {
    @Getter final int id;
    @Getter final String name;
    @Getter final List<Restaurant> restaurants;

    public Mall(int id, String name, List<Restaurant> restaurants) {
        this.id = id;
        this.name = name;
        this.restaurants = restaurants;
    }
}
