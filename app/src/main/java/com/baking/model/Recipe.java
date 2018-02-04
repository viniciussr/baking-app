package com.baking.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

@AutoValue
public abstract class Recipe {

    public abstract Long id();
    public abstract String name();
    public abstract List<Ingredient> ingredients();
    public abstract List<Step> steps();
    public abstract Long servings();
    public abstract String image();
    
    @AutoValue.Builder
    abstract static class Builder {
         abstract Builder id(Long id);
         abstract Builder name(String name);
         abstract Builder ingredients(List<Ingredient> ingredients);
         abstract Builder steps(List<Step> steps);
         abstract Builder servings(Long servings);
         abstract Builder image(String image);
         abstract Recipe build();
    }

    static Builder builder() {
        return new AutoValue_Recipe.Builder();
    }

    public static TypeAdapter<Recipe> typeAdapter(Gson gson) {
        return new AutoValue_Recipe.GsonTypeAdapter(gson);
    }

}


