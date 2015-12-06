package com.keenant.mealdeals.cove;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.keenant.mealdeals.Constants;
import com.keenant.mealdeals.cove.parser.DealParser;
import com.keenant.mealdeals.cove.parser.ListParser;
import com.keenant.mealdeals.cove.parser.MallParser;
import com.keenant.mealdeals.cove.parser.Parser;
import com.keenant.mealdeals.cove.parser.RestaurantParser;
import com.keenant.mealdeals.cove.parser.UserParser;
import com.keenant.mealdeals.data.Deal;
import com.keenant.mealdeals.data.Mall;
import com.keenant.mealdeals.data.Restaurant;
import com.keenant.mealdeals.data.User;
import com.securepreferences.SecurePreferences;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;

public class Cove {
    @Getter static Cove instance;

    public static Cove getInstance(Context context) {
        if (instance != null)
            return instance;
        return new Cove(context);
    }

    @Getter private Context context;

    @Getter private List<Mall> malls;
    @Getter private List<Restaurant> restaurants;
    @Getter private List<Deal> deals;

    @Getter boolean setup;

    @Getter private User user;
    private SharedPreferences prefs;

    public Cove(Context context) {
        instance = this;

        this.context = context;
        this.prefs = new SecurePreferences(context, (String) null, "prefs.xml");

        setup();
    }

    private void setup() {
        setup = false;

        final Fetcher fetchMalls = fetch("/malls.json", new ListParser<>(new MallParser()), new CoveCallback<List<Mall>>() {
            @Override
            public void success(List<Mall> value) {
                Cove.this.malls = value;
            }

            @Override
            public void failure(int statusCode, String body) {

            }
        });

        final Fetcher fetchRestaurants = fetch("/restaurants.json", new ListParser<>(new RestaurantParser()), new CoveCallback<List<Restaurant>>() {
            @Override
            public void success(List<Restaurant> value) {
                Cove.this.restaurants = value;
            }

            @Override
            public void failure(int statusCode, String body) {

            }
        });

        final Fetcher fetchDeals = fetch("/deals.json", new ListParser<>(new DealParser()), new CoveCallback<List<Deal>>() {
            @Override
            public void success(List<Deal> value) {
                Cove.this.deals = value;
                setup = true;
            }

            @Override
            public void failure(int statusCode, String body) {

            }
        });

        login(getUsername(), getPassword(), null);

        if (user != null)
            fetchDeals.getParams().put("user", user.getId());

        new FetcherChain(fetchMalls, fetchRestaurants, fetchDeals).execute();
    }

    public String getUsername() {
        return prefs.getString("username", null);
    }

    public String getPassword() {
        return prefs.getString("password", null);
    }

    public void login(String username, String password, final CoveCallback<User> callback) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("username", username);
        edit.putString("password", password);
        edit.apply();

        Fetcher fetchUser = fetch("/users/auth.json?username=" + username + "&password=" + password, new UserParser(), new CoveCallback<User>() {
            @Override
            public void success(User value) {
                Cove.this.user = value;
                if (callback != null)
                    callback.success(value);
            }

            @Override
            public void failure(int statusCode, String body) {
                Cove.this.user = null;
                if (callback != null)
                    callback.failure(statusCode, body);
            }
        });

        fetchUser.execute();
    }

    public void afterSetup(final Runnable runnable) {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (setup) {
                    Handler handler = new Handler(context.getMainLooper());
                    handler.post(runnable);
                    timer.cancel();
                }
            }
        }, 0, 50);
    }

    public Mall getMall(int id) {
        for (Mall test : malls)
            if (test.getId() == id)
                return test;
        throw new RuntimeException("no mall " +  id);
    }

    public Restaurant getRestaurant(int id) {
        for (Restaurant test : restaurants)
            if (test.getId() == id)
                return test;
        throw new RuntimeException("no restaraurant " +  id);
    }

    public Deal getDeal(int id) {
        for (Deal test : deals)
            if (test.getId() == id)
                return test;
        throw new RuntimeException("no deal " +  id);
    }

    private String url(String path) {
        return Constants.API + path;
    }

    public <T> Fetcher<T> fetch(final String path, final Parser<T> parser, final CoveCallback<T> callback) {
        return new Fetcher<>(url(path), parser, callback);
    }
}
