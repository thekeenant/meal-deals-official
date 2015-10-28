package com.keenant.mealdeals.cove.parser;

public interface Parser<T> {
    T parse(String content) throws ParserException;
}
