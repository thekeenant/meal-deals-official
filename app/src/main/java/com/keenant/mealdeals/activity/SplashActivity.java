package com.keenant.mealdeals.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        final Cove cove = Cove.getInstance(this);
        cove.afterSetup(new Runnable() {
            @Override
            public void run() {
                if (cove.getUser() == null) {
                    signup();
                }
                else {
                    skip();
                }
            }
        });
    }

    private void skip() {
        Intent intent = new Intent(this, MallsActivity.class);
        startActivity(intent);
    }

    private void signup() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
