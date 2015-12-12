package com.keenant.mealdeals.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.keenant.mealdeals.MallHolder;
import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Mall;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.easyadapter.EasyAdapter;

public class MallsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.mall_list)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malls);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView text = (TextView) findViewById(R.id.title_text);
        text.setText("Select a Mall");

        EasyAdapter<Mall> adapter = new EasyAdapter<>(this, MallHolder.class, Cove.getInstance(this).getMalls());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mall mall = Cove.getInstance().getMalls().get(i);
                mall(mall);
            }
        });
    }

    private void mall(Mall mall) {
        Intent intent = new Intent(this, CategoriesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("mall", mall.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
