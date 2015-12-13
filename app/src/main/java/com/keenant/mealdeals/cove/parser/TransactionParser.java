package com.keenant.mealdeals.cove.parser;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.data.Transaction;

public class TransactionParser implements Parser<Transaction> {
    @Override
    public Transaction parse(String content) throws ParserException {
        JsonObject json = new JsonObjectParser().parse(content);
        int userId = json.get("user_id").getAsInt();
        int dealId = json.get("deal_id").getAsInt();
        int points = json.get("points").getAsInt();

        return new Transaction(userId, dealId, points);
    }
}
