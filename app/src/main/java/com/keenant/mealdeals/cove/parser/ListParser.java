package com.keenant.mealdeals.cove.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class ListParser<T> implements Parser<List<T>> {
    private final Parser<T> childParser;

    public ListParser(Parser<T> childParser) {
        this.childParser = childParser;
    }

    @Override
    public List<T> parse(String content) throws ParserException {
        JsonArray array = new JsonArrayParser().parse(content);
        return parse(array);
    }

    public List<T> parse(JsonArray array) throws ParserException {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            String str = new Gson().toJson(element);
            list.add(childParser.parse(str));
        }
        return list;
    }
}
