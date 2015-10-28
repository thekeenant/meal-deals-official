package com.keenant.mealdeals.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Deal;
import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.logo_text_padding);

        int dealId = getIntent().getExtras().getInt("deal");
        Deal deal = Cove.getInstance(this).getDeal(dealId);

        dealText.setText(deal.getText());
        dealRestaurant.setText(deal.getRestaurant().getName());
        dealLocation.setText(deal.getRestaurant().getLocation());
        dealDetails.setText(deal.getDetails());

        Picasso.with(this).load(deal.getImageUri()).into(banner);
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
