package com.keenant.mealdeals.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.keenant.mealdeals.cove.Cove;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Restaurant {
    @Getter final int id;
    @Getter final Mall mall;
    @Getter final String name;
    @Getter final String location;
    @Getter final Uri imageUri;
    @Getter Bitmap image;

    public Restaurant(int id, Mall mall, String name, String location, Uri imageUri) {
        this.id = id;
        this.mall = mall;
        this.name = name;
        this.location = location;
        this.imageUri = imageUri;
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
//                throw new RuntimeException("Failed to load deal image!");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
