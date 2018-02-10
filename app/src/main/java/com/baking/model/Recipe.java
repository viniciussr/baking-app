package com.baking.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

@AutoValue
public abstract class Recipe implements Parcelable{

    public abstract Long id();
    public abstract String name();
    public abstract List<Ingredient> ingredients();
    public abstract List<Step> steps();
    public abstract Long servings();
    public abstract String image();


    public static Recipe create(Long id, String name, List<Ingredient> ingredients, List<Step> steps,Long servings,String image ) {
        return new AutoValue_Recipe( id,  name,  ingredients,  steps, servings, image);
    }

    public static TypeAdapter<Recipe> typeAdapter(Gson gson) {
        return new AutoValue_Recipe.GsonTypeAdapter(gson);
    }

}


