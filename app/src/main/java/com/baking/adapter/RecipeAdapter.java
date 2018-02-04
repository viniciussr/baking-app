package com.baking.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baking.R;
import com.baking.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipes;
//    final RecipeClickHandler recipeClickHandler;

    public RecipeAdapter(ArrayList<Recipe> recipes){
        this.recipes = recipes;
//        this.recipeClickHandler = null;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

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

    class RecipeViewHolder extends RecyclerView.ViewHolder  {

        Long id;

        @BindView(R.id.recipe_name)
        TextView recipeName;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            recipeClickHandler.onClick(id);
//        }

        protected void bind(Recipe recipe){
            id = recipe.id();
            recipeName.setText(recipe.name());
        }

    }

    public interface RecipeClickHandler {
        void onClick(Long id);
    }
}
