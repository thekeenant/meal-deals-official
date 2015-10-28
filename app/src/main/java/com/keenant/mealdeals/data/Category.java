package com.keenant.mealdeals.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.keenant.mealdeals.cove.Cove;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import lombok.Getter;

public class Category {
    @Getter final int id;
    @Getter final String name;
    @Getter final Uri imageUri;
    @Getter Bitmap image;

    public Category(int id, String name, Uri imageUri) {
        this.id = id;
        this.name = name;
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
                throw new RuntimeException("Failed to load deal image!");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
