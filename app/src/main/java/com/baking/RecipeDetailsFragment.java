package com.baking;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baking.model.Ingredient;
import com.baking.model.Recipe;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeDetailsFragment extends Fragment {

    @BindString(R.string.intent_detail_put_extra)
    String intentDetail;
    @BindView(R.id.recipe_details_ingredients)
    TextView recipeDetailsIngredients;

    final static String INGREDIENT_HEADER = "Ingredients:";

    private Recipe recipe;

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
        ButterKnife.bind(this,view);
        recipe = (Recipe) getActivity().getIntent().getExtras().getParcelable(intentDetail);
        showIngredients(recipe.ingredients());
        return view;

    }

    public void showIngredients(List<Ingredient> ingredients) {

        StringBuilder sb = new StringBuilder();
        sb.append(INGREDIENT_HEADER);

        for (Ingredient ingredient : ingredients) {
            String name = ingredient.ingredient();
            float quantity = ingredient.quantity();
            String measure = ingredient.measure();
            sb.append("\n");
            sb.append(name +" - "+quantity+" - "+measure);
        }

        recipeDetailsIngredients.setText(sb);

    }


}
