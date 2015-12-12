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

        mall = Cove.getInstance(this).getMall(getIntent().getExtras().getInt("mall"));

        TextView text = (TextView) findViewById(R.id.title_text);
        text.setText("Select a Category");

        List<Category> list = new ArrayList<>();
        list.add(new Category(1, "Korean", Uri.parse("https://cdn0.iconfinder.com/data/icons/kameleon-free-pack-rounded/110/Food-Dome-128.png")));
        list.add(new Category(2, "Italian", Uri.parse("https://cdn0.iconfinder.com/data/icons/kameleon-free-pack-rounded/110/Money-Graph-128.png")));
        list.add(new Category(3, "Vietnamese", Uri.parse("https://cdn3.iconfinder.com/data/icons/linecons-free-vector-icons-pack/32/food-128.png")));
        list.add(new Category(4, "Chinese", Uri.parse("https://cdn2.iconfinder.com/data/icons/thesquid-ink-40-free-flat-icon-pack/64/carrot-128.png")));

        categoryAdapter = new EasyAdapter<>(this, CategoryHolder.class, list);
        categoryList.setAdapter(categoryAdapter);

        Cove.getInstance(this).afterSetup(new Runnable() {
            @Override
            public void run() {
                // todo add items
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
