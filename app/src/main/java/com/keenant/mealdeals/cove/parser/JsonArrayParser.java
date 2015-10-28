package com.keenant.mealdeals.cove.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class JsonArrayParser implements Parser<JsonArray> {
    @Override
    public JsonArray parse(String content) throws ParserException {
        return new Gson().fromJson(content, JsonArray.class);
    }
}
