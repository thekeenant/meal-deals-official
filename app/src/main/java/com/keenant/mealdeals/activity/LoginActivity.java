package com.keenant.mealdeals.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.keenant.mealdeals.Header;
import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.cove.CoveCallback;
import com.keenant.mealdeals.cove.Fetcher;
import com.keenant.mealdeals.data.User;
import com.squareup.okhttp.Credentials;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.username)
    EditText usernameText;

    @Bind(R.id.password)
    EditText passwordText;

    @Bind(R.id.signup_link)
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        Cove.getInstance(this).login(username, password, new CoveCallback<User>() {
            @Override
            public void success(User value) {
                login();
            }

            @Override
            public void failure(int statusCode, String body) {
                fail();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        
    }

    public void fail() {

    }
}
