package com.keenant.mealdeals.cove.parser;

import android.net.Uri;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Mall;
import com.keenant.mealdeals.data.Restaurant;

public class RestaurantParser implements Parser<Restaurant> {
    @Override
    public Restaurant parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        Mall mall = Cove.getInstance().getMall(json.get("mall_id").getAsInt());
        String location = json.get("location").getAsString();
        
        return new Restaurant(id, mall, name, location, Uri.parse(""));
    }
}
