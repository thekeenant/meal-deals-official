package com.keenant.mealdeals.cove;

import android.os.Handler;
import android.os.Looper;

import java.util.Arrays;
import java.util.List;

public class FetcherChain {
    private List<Fetcher> chain;

    public FetcherChain(Fetcher... chain) {
        this.chain = Arrays.asList(chain);
    }

    public void execute() {
        for (int i = 0; i < chain.size() - 1; i++) {
            final int index = i;
            chain.get(i).setPostExecute(new Runnable() {
                @Override
                public void run() {
                    chain.get(index + 1).execute();
                }
            });
        }

        chain.get(0).execute();
    }
}
