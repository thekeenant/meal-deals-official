package com.keenant.mealdeals;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keenant.mealdeals.data.Deal;
import com.keenant.mealdeals.data.Mall;
import com.squareup.picasso.Picasso;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_mall)
public class MallHolder extends ItemViewHolder<Mall> {

    @ViewId(R.id.mall_name)
    TextView text;

    public MallHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Mall mall, PositionInfo positionInfo) {
        text.setText(mall.getName());
    }
}
