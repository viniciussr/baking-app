package com.baking.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

@AutoValue
public abstract class Ingredient {

    public abstract Float quantity();
    public abstract String measure();
    public abstract String ingredient();

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder quantity(Float quantity);
        abstract Builder measure(String measure);
        abstract Builder ingredient(String ingredient);
        abstract Ingredient build();
    }

    static Builder builder() {
        return new AutoValue_Ingredient.Builder();
    }

    public static TypeAdapter<Ingredient> typeAdapter(Gson gson) {
        return new AutoValue_Ingredient.GsonTypeAdapter(gson);
    }

}
