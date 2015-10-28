package com.keenant.mealdeals.cove.parser;

public class ParserException extends Exception {
    public ParserException(String error, Exception cause) {
        super(error, cause);
    }

    public ParserException(String error) {
        super(error);
    }
}
