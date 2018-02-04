package com.baking.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

@AutoValue
public abstract class Step {

    public abstract Long id();
    public abstract String shortDescription();
    public abstract String description();
    public abstract String videoURL();
    public abstract String thumbnailURL();

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder id(Long id);
        abstract Builder shortDescription(String name);
        abstract Builder description(String ingredients);
        abstract Builder videoURL(String steps);
        abstract Builder thumbnailURL(String servings);
        abstract Step build();
    }

    static Builder builder() {
        return new AutoValue_Step.Builder();
    }

    public static TypeAdapter<Step> typeAdapter(Gson gson) {
        return new AutoValue_Step.GsonTypeAdapter(gson);
    }
}
