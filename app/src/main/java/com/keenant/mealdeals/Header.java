package com.keenant.mealdeals;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

public class Header {
    private final Context context;
    private final String title;
    private final ListView listView;
    private final View header;
    private final TextView text;

    public Header(Activity context, String title, ListView listView) {
        this.context = context;
        this.title = title;
        this.listView = listView;
        this.header = context.findViewById(R.id.header);
        this.text = (TextView) context.findViewById(R.id.header_text);
        setup();
    }

    private void setup() {
        this.text.setText(title);
    }

    private void hide() {
        header.animate().translationY(-header.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void show() {
        header.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }
}
