package com.keenant.mealdeals.cove;

import android.os.Handler;
import android.util.Log;

import com.keenant.mealdeals.cove.parser.Parser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fetcher<T> {
    private final String url;
    private final Parser<T> parser;
    private final CoveCallback<T> callback;
    @Getter final RequestParams params = new RequestParams();

    @Getter boolean complete;
    @Getter @Setter Runnable postExecute;

    public Fetcher(String url, Parser<T> parser, CoveCallback<T> callback) {
        this.url = url;
        this.parser = parser;
        this.callback = callback;
    }

    public Fetcher execute() {
        return execute("GET");
    }

    public Fetcher execute(String method) {
        if (complete)
            throw new RuntimeException("fetcher already executed");

        final Handler handler = new Handler(Cove.getInstance().getContext().getMainLooper());

        AsyncHttpClient client = new AsyncHttpClient();

        AsyncHttpResponseHandler response = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("Meals", "Fetch Success: " + url);
                try {
                    String body = new String(responseBody);
                    T value = parser.parse(body);
                    callback.success(value);
                } catch (Exception e) {
                    throw new RuntimeException("fatal crash when success fetching '" + url + "'", e);
                }

                complete = true;
                if (postExecute != null)
                    handler.post(postExecute);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Meals", "Fetch Failed: " + url);
                try {
                    String body = new String(responseBody);
                    callback.failure(statusCode, body);
                } catch (Exception e) {
                    throw new RuntimeException("fatal crash when failed fetching '" + url + "'", e);
                }

                complete = true;
                if (postExecute != null)
                    handler.post(postExecute);
            }
        };

        if (method.equalsIgnoreCase("GET"))
            client.get(url, params, response);
        else if (method.equalsIgnoreCase("POST"))
            client.post(url, params, response);
        else
            throw new RuntimeException("Invalid http method");

        return this;
    }
}
