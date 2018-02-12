package com.baking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baking.R;
import com.baking.activity.RecipeDetailsActivity;
import com.baking.activity.RecipeStepActivity;
import com.baking.adapter.RecipeDetailsAdapter;
import com.baking.model.Ingredient;
import com.baking.model.Recipe;
import com.baking.model.Step;

import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeDetailsFragment extends Fragment implements RecipeDetailsAdapter.RecipeDetailsClickOnClickHandler
{

    final static String INGREDIENT_HEADER = "Ingredients:";

    @BindView(R.id.recipe_details_ingredients)
    TextView recipeDetailsIngredients;
    @BindView(R.id.recipe_details_steps)
    RecyclerView recyclerViewSteps;

    @BindString(R.string.intent_detail_put_extra)
    String intentDetail;
    @BindString(R.string.step_position)
    String stepPosition;

    @BindBool(R.bool.two_pane_mode)
    boolean twoPaneMode;

    private RecipeDetailsAdapter adapter;
    private Recipe recipe;
    private Unbinder unbinder;


    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        unbinder = ButterKnife.bind(this,view);
        recipe = (Recipe) getActivity().getIntent().getExtras().getParcelable(intentDetail);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadIngredients();
        loadSteps();
    }


    public void loadIngredients() {
        StringBuilder sb = new StringBuilder();
        sb.append(INGREDIENT_HEADER);
        for (Ingredient ingredient : recipe.ingredients()) {
            String name = ingredient.ingredient();
            float quantity = ingredient.quantity();
            String measure = ingredient.measure();
            sb.append("\n");
            sb.append(name +" - "+quantity+" - "+measure);
        }
        recipeDetailsIngredients.setText(sb);
    }

    private void loadSteps(){
        recyclerViewSteps.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSteps.setLayoutManager(layoutManager);
        adapter = new RecipeDetailsAdapter(this);
        adapter.setStepData(recipe.steps());
        recyclerViewSteps.setAdapter(adapter);
    }

    @Override
    public void onClick(Step step, int position) {
        Intent intent = new Intent(getActivity(), RecipeStepActivity.class);
        intent.putExtra(intentDetail, recipe.steps());
        intent.putExtra(stepPosition,position);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
