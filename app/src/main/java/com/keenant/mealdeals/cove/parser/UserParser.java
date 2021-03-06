package com.keenant.mealdeals.cove.parser;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.data.Transaction;
import com.keenant.mealdeals.data.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserParser implements Parser<User> {
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Override
    public User parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);

        if (json.entrySet().size() == 0)
            return null;

        int id = json.get("id").getAsInt();
        String email = json.get("email").getAsString();
        Date dob = null;
        try {
            dob = dateformat.parse(json.get("dob").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Transaction> transactions = new ArrayList<>();
        if (json.has("transactions"))
            transactions.addAll(new ListParser<>(new TransactionParser()).parse(json.getAsJsonArray("transactions")));

        return new User(id, email, dob, transactions);
    }
}
