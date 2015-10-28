package com.keenant.mealdeals;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keenant.mealdeals.data.Category;
import com.keenant.mealdeals.data.Deal;
import com.squareup.picasso.Picasso;

import java.util.Random;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_category)
public class CategoryHolder extends ItemViewHolder<Category> {

    @ViewId(R.id.category_icon)
    ImageView icon;

    @ViewId(R.id.category_name)
    TextView name;

    public CategoryHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Category category, PositionInfo positionInfo) {
        name.setText(category.getName() + " (" + new Random().nextInt(50) + ")");

        Picasso.with(getContext()).load(category.getImageUri()).into(icon);
    }
}
