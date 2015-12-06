package com.keenant.mealdeals.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.cove.CoveCallback;
import com.keenant.mealdeals.cove.Fetcher;
import com.keenant.mealdeals.cove.parser.JsonObjectParser;
import com.keenant.mealdeals.data.Code;
import com.keenant.mealdeals.data.Deal;
import com.loopj.android.http.AsyncHttpClient;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DealActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.deal_banner)
    ImageView banner;

    @Bind(R.id.deal_text)
    TextView dealText;

    @Bind(R.id.deal_restaurant)
    TextView dealRestaurant;

    @Bind(R.id.deal_location)
    TextView dealLocation;

    @Bind(R.id.deal_details)
    TextView dealDetails;

    @Bind(R.id.deal_points)
    TextView dealPoints;

    @Bind(R.id.deal_logo)
    ImageView dealLogo;

    @Bind(R.id.deal_claim)
    Button dealClaim;

    private Deal deal;

    private EditText[] code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int dealId = getIntent().getExtras().getInt("deal");
        this.deal = Cove.getInstance(this).getDeal(dealId);

        dealText.setText(deal.getText());
        dealRestaurant.setText(deal.getRestaurant().getName());
        dealLocation.setText(deal.getRestaurant().getLocation());
        dealDetails.setText(deal.getDetails());
        dealPoints.setText("+" + deal.getPoints());
        Picasso.with(this).load(deal.getRestaurant().getImageUri()).into(dealLogo);

        dealClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog().show();
            }
        });

        Picasso.with(this).load(deal.getImageUri()).into(banner);
    }

    private AlertDialog dialog() {
        AlertDialog.Builder codeDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.popup_code, null);

        codeDialog.setTitle("Deal Code");
        codeDialog.setMessage("Ask for the Meal Deal code!");
        codeDialog.setCancelable(true);
        codeDialog.setView(view);

        final EditText pin1 = (EditText) view.findViewById(R.id.pin_1);
        final EditText pin2 = (EditText) view.findViewById(R.id.pin_2);
        final EditText pin3 = (EditText) view.findViewById(R.id.pin_3);
        final EditText pin4 = (EditText) view.findViewById(R.id.pin_4);
        code = new EditText[] {pin1, pin2, pin3, pin4};

        for (int x = 0; x < code.length; x++) {
            final EditText pin = code[x];

            final int i = x;

            pin.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (i + 1 <= code.length - 1) {
                        EditText next = code[i + 1];
                        if (pin.getText().toString().length() == 1)
                            next.requestFocus();
                    }
                    else if (pin.getText().toString().length() == 1) {
                        attemptDeal();
                    }
                    if (i - 1 >= 0) {
                        EditText prev = code[i - 1];
                        if (s.length() == 0)
                            prev.requestFocus();
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            if (x - 1 >= 0) {
                final EditText prev = code[x - 1];

                pin.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            prev.requestFocus();
                        return false;
                    }
                });
            }

        }

        return codeDialog.create();
    }

    private void attemptDeal() {
        String nums = code[0].getText().toString() + code[1].getText().toString() + code[2].getText().toString() + code[3].getText().toString();
        Code code = new Code(Integer.parseInt(nums), new Date());

        boolean ok = deal.getRestaurant().getCode().matches(code);

        if (ok) {
            Fetcher fetcher = Cove.getInstance().fetch("/transactions.json", new JsonObjectParser(), new CoveCallback<JsonObject>() {
                @Override
                public void success(JsonObject value) {
                    Intent intent = new Intent(DealActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }

                @Override
                public void failure(int statusCode, String body) {
                    throw new RuntimeException("oops");
                }
            });

            fetcher.getParams().put("transaction[user_id]", Cove.getInstance().getUser().getId());
            fetcher.getParams().put("transaction[deal_id]", deal.getId());

            fetcher.execute("POST");
        }
        else {
            Toast.makeText(this, "Invalid code.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
