package com.keenant.mealdeals.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.keenant.mealdeals.cove.Cove;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Restaurant {
    @Getter final int id;
    @Getter final List<Mall> malls;
    @Getter final String name;
    @Getter final String location;
    @Getter final String about;
    @Getter final Uri imageUri;
    @Getter final Code code;
    @Getter Bitmap image;

    public Restaurant(int id, List<Mall> malls, String name, String location, String about, Uri imageUri, Code code) {
        this.id = id;
        this.malls = malls;
        this.name = name;
        this.location = location;
        this.about = about;
        this.imageUri = imageUri;
        this.code = code;
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
