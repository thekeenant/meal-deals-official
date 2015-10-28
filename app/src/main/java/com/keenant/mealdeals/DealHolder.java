package com.keenant.mealdeals;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keenant.mealdeals.data.Deal;
import com.squareup.picasso.Picasso;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_deal)
public class DealHolder extends ItemViewHolder<Deal> {

    @ViewId(R.id.deal_text)
    TextView text;

    @ViewId(R.id.deal_image)
    ImageView image;

    @ViewId(R.id.deal_restaurant)
    TextView restaurant;

    @ViewId(R.id.deal_location)
    TextView location;

    public DealHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Deal deal, PositionInfo positionInfo) {
        text.setText(deal.getText());
        restaurant.setText(deal.getRestaurant().getName());
        location.setText(deal.getRestaurant().getLocation());

        Picasso.with(getContext()).load(deal.getImageUri()).into(image);
    }
}
