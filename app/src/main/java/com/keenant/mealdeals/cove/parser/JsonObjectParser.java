package com.keenant.mealdeals.cove.parser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonObjectParser implements Parser<JsonObject> {
    @Override
    public JsonObject parse(String content) throws ParserException {
        return new Gson().fromJson(content, JsonObject.class);
    }
}
