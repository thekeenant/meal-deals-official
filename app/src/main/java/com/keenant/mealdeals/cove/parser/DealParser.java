package com.keenant.mealdeals.cove.parser;

import android.net.Uri;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Deal;
import com.keenant.mealdeals.data.Restaurant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DealParser implements Parser<Deal> {
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    @Override
    public Deal parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);
        int id = json.get("id").getAsInt();
        Restaurant restaurant = Cove.getInstance().getRestaurant(json.get("restaurant_id").getAsInt());
        String text = json.get("text").getAsString();
        String details = json.get("details").getAsString();
        Uri imageUri = Uri.parse(json.get("image_large").getAsString());
        Date date = null;
        try {
            date = dateformat.parse(json.get("date").getAsString());
        } catch (ParseException e) {
            throw new ParserException("failed to read date", e);
        }
        String code = json.get("code").getAsString();

        return new Deal(id, restaurant, null, text, details, imageUri, date, code);
    }
}
