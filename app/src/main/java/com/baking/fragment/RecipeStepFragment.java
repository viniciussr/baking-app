package com.baking.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baking.R;
import com.baking.adapter.RecipeStepAdapter;
import com.baking.model.Recipe;
import com.baking.model.Step;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepFragment extends Fragment {

    @BindView(R.id.recipe_step_viewpager)
    ViewPager recipeStepViewPager;

    @BindString(R.string.intent_detail_put_extra)
    String intentDetail;
    @BindString(R.string.step_position)
    String intentStepPosition;

    private RecipeStepAdapter stepAdapter;
    private ArrayList<Step> steps;
    private int stepPosition;
    private int mPreviousPos;

    Unbinder unbinder;

    public RecipeStepFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        unbinder = ButterKnife.bind(this, view);
        steps = getActivity().getIntent().getExtras().getParcelableArrayList(intentDetail);
        stepPosition = getActivity().getIntent().getExtras().getInt(intentStepPosition);
        stepAdapter = new RecipeStepAdapter(getFragmentManager(), steps, getContext());
        recipeStepViewPager.setAdapter(stepAdapter);
        recipeStepViewPager.setCurrentItem(stepPosition);
        stepAdapter.notifyDataSetChanged();
        setUpViewPagerListener();

        return view;
    }

    private void setUpViewPagerListener() {
        recipeStepViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                try {
                    ((RecipeStepItemFragment) stepAdapter.getItem(mPreviousPos)).onStop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPreviousPos = position;

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
