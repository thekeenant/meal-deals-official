package com.keenant.mealdeals.cove.parser;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.data.Mall;
import com.keenant.mealdeals.data.Restaurant;

import java.util.ArrayList;

public class MallParser implements Parser<Mall> {
    @Override
    public Mall parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();

        return new Mall(id, name, new ArrayList<Restaurant>());
    }
}
