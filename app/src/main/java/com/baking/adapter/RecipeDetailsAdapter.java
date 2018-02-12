package com.baking.adapter;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baking.R;
import com.baking.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vinicius.rocha on 2/10/18.
 */

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.RecipeDetailsViewHolder>{

    private ArrayList<Step> steps;
    private final RecipeDetailsClickOnClickHandler recipeDetailsClickOnClickHandler;


    public RecipeDetailsAdapter(RecipeDetailsClickOnClickHandler recipeDetailsClickOnClickHandler){
        this.recipeDetailsClickOnClickHandler = recipeDetailsClickOnClickHandler;
    }

    @Override
    public RecipeDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe_details_item, parent, false);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        return new RecipeDetailsAdapter.RecipeDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeDetailsViewHolder holder, int position) {
        holder.bind(steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void setStepData(ArrayList<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    class RecipeDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.step_description)
        TextView stepDescription;
        @BindView(R.id.icon_step)
        ImageView iconStep;

        public RecipeDetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Step step = steps.get(adapterPosition);
            recipeDetailsClickOnClickHandler.onClick(step, adapterPosition);
        }

        protected void bind(Step step){
            stepDescription.setText(step.shortDescription());
            if(step.videoURL().isEmpty()){
                iconStep.setVisibility(View.INVISIBLE);
            }
        }
    }

    public interface RecipeDetailsClickOnClickHandler {
        void onClick(Step step, int position);
    }
}
