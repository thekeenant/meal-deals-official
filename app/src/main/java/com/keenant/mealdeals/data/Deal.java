package com.keenant.mealdeals.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.keenant.mealdeals.cove.Cove;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Deal {
    @Getter final int id;
    @Getter final Restaurant restaurant;
    @Getter final Category category;
    @Getter final Mall mall;
    @Getter final String text;
    @Getter final String details;
    @Getter final Uri imageUri;
    @Getter final int points;
    @Getter final boolean halal;

    @Getter Bitmap image;

    public Deal(int id, Restaurant restaurant, Category category, Mall mall, String text, String details, Uri imageUri, int points, boolean halal) {
        this.id = id;
        this.restaurant = restaurant;
        this.category = category;
        this.mall = mall;
        this.text = text;
        this.details = details;
        this.imageUri = imageUri;
        this.points = points;
        this.halal = halal;
        retrieveImage();
    }

    private void retrieveImage() {
        Picasso.with(Cove.getInstance().getContext()).load(imageUri).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                image = bitmap;
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                throw new RuntimeException("Failed to load deal image!");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
