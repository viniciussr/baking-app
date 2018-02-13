package com.baking.idlingresource;


/**
 * Created by vinicius.rocha on 2/13/18.
 */

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

public class RecipeIdlingResource implements IdlingResource {

    private Boolean isIdleNow = Boolean.TRUE;

    @Nullable
    private volatile ResourceCallback callback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIdleState(boolean idleState) {
        isIdleNow = idleState;
        if (idleState && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}
