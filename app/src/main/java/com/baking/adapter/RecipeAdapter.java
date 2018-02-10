package com.baking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baking.R;
import com.baking.model.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipes;
    private final RecipeClickOnClickHandler recipeClickOnClickHandler;

    public RecipeAdapter(RecipeClickOnClickHandler clickHandler){
        recipeClickOnClickHandler = clickHandler;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe_list_item, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(recipes.get(position));

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipeData(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener {

        Long id;

        @BindView(R.id.recipe_name)
        TextView recipeName;

        @BindView(R.id.recipe_serving)
        TextView recipeServing;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = recipes.get(adapterPosition);
            recipeClickOnClickHandler.onClick(recipe);
        }

        protected void bind(Recipe recipe){
            id = recipe.id();
            recipeName.setText(recipe.name());
            recipeServing.setText(recipe.servings().toString());
        }

    }

    public interface RecipeClickOnClickHandler {
        void onClick(Recipe recipe);
    }
}
