package com.keenant.mealdeals.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Deal;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DealSealedActivity extends AppCompatActivity {

    private Deal deal;

    @Bind(R.id.newPoints)
    TextView newPoints;

    @Bind(R.id.totalPoints)
    TextView totalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_sealed);
        ButterKnife.bind(this);

        this.deal = Cove.getInstance(this).getDeal(getIntent().getExtras().getInt("deal"));

        newPoints.setText("+" + deal.getPoints() + " points");
        totalPoints.setText(Cove.getInstance().getUser().getPoints() + " TOTAL POINTS");
    }
}
