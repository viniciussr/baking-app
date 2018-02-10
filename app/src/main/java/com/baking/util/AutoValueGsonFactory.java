package com.baking.util;


import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;


/**
 * Created by vinicius.rocha on 2/4/18.
 */

@GsonTypeAdapterFactory
public abstract  class AutoValueGsonFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueGsonFactory();
    }
}
