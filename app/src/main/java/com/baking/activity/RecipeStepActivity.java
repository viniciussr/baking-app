package com.baking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baking.R;

import butterknife.ButterKnife;

public class RecipeStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        ButterKnife.bind(this);
    }
}
