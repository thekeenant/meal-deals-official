package com.keenant.mealdeals.cove.parser;

import android.net.Uri;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Category;
import com.keenant.mealdeals.data.Deal;
import com.keenant.mealdeals.data.Mall;
import com.keenant.mealdeals.data.Restaurant;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CategoryParser implements Parser<Category> {
    @Override
    public Category parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        Uri icon = Uri.parse(json.get("icon_small").getAsString());

        return new Category(id, name, icon);
    }
}
