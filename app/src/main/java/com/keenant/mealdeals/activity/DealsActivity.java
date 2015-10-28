package com.keenant.mealdeals.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.keenant.mealdeals.DealHolder;
import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Deal;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.easyadapter.EasyAdapter;

public class DealsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.deal_list)
    ListView dealList;
    EasyAdapter<Deal> dealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.logo_text_padding);

        dealAdapter = new EasyAdapter<>(this, DealHolder.class);
        dealList.setAdapter(dealAdapter);

        Cove.getInstance(this).afterSetup(new Runnable() {
            @Override
            public void run() {
                dealAdapter.addItems(Cove.getInstance().getDeals());
                dealAdapter.notifyDataSetChanged();
            }
        });

        dealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Deal deal = dealAdapter.getItem(index);
                Intent intent = new Intent(getApplicationContext(), DealActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("deal", deal.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_malls) {
            Intent intent = new Intent(this, MallsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_splash) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_categories) {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
