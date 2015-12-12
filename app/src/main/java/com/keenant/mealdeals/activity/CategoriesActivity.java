package com.keenant.mealdeals.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.keenant.mealdeals.CategoryHolder;
import com.keenant.mealdeals.DealHolder;
import com.keenant.mealdeals.R;
import com.keenant.mealdeals.cove.Cove;
import com.keenant.mealdeals.data.Category;
import com.keenant.mealdeals.data.Mall;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.easyadapter.EasyAdapter;

public class CategoriesActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.category_list)
    ListView categoryList;
    EasyAdapter<Category> categoryAdapter;

    private static Mall mall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView text = (TextView) findViewById(R.id.title_text);
        text.setText("Select a Category");

        mall = Cove.getInstance(this).getMall(getIntent().getExtras().getInt("mall"));

        categoryAdapter = new EasyAdapter<>(this, CategoryHolder.class, new ArrayList<Category>());
        categoryList.setAdapter(categoryAdapter);

        Cove.getInstance(this).afterSetup(new Runnable() {
            @Override
            public void run() {
                // todo add items
                categoryAdapter.addItems(Cove.getInstance().getCategories());
                categoryAdapter.notifyDataSetChanged();
            }
        });

        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Category category = categoryAdapter.getItem(index);
                Intent intent = new Intent(getApplicationContext(), DealsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("mall", mall.getId());
                bundle.putInt("category", category.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
