package com.baking.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baking.fragment.RecipeStepItemFragment;
import com.baking.model.Step;

import java.util.List;

/**
 * Created by vinicius.rocha on 2/12/18.
 */

public class RecipeStepAdapter extends FragmentPagerAdapter {

    private List<Step> steps;

    public RecipeStepAdapter(FragmentManager fm, List<Step> steps, Context context) {
        super(fm);
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeStepItemFragment.newInstance(steps.get(position));
    }

    @Override
    public int getCount() {
        return steps.size();
    }
}
