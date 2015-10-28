package com.keenant.mealdeals.cove;

public interface CoveCallback<T> {
    void success(T value);

    void failure(int statusCode, String body);
}
