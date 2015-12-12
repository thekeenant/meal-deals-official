package com.keenant.mealdeals.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.cove.CoveCallback;
import com.keenant.mealdeals.data.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.email)
    EditText emailText;

    @Bind(R.id.password)
    EditText passwordText;

    @Bind(R.id.login)
    Button loginButton;

    @Bind(R.id.signup_link)
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                Cove.getInstance(LoginActivity.this).login(email, password, new CoveCallback<User>() {
                    @Override
                    public void success(User value) {
                        login();
                    }

                    @Override
                    public void failure(int statusCode, String body) {
                        fail();
                    }
                });
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
        Intent intent = new Intent(this, MallsActivity.class);
        startActivity(intent);
    }

    public void fail() {
        Toast.makeText(this, "Invaild usernmae & password.", Toast.LENGTH_LONG).show();
    }
}
