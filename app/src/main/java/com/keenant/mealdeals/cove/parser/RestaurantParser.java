package com.keenant.mealdeals.cove.parser;

import android.net.Uri;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Code;
import com.keenant.mealdeals.data.Mall;
import com.keenant.mealdeals.data.Restaurant;

import java.util.Date;
import java.util.List;

public class RestaurantParser implements Parser<Restaurant> {
    @Override
    public Restaurant parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        List<Mall> malls = new ListParser<>(new MallParser()).parse(json.getAsJsonArray("malls"));
        String location = json.get("location").getAsString();
        Uri imageUri = Uri.parse(json.get("logo").getAsString());
        
        return new Restaurant(id, malls, name, location, imageUri, new Code(5555, new Date()));
    }
}
