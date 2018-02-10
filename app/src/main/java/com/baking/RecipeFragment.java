package com.baking;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baking.adapter.RecipeAdapter;
import com.baking.model.Recipe;
import com.baking.service.RecipeService;
import com.baking.service.ServiceFactory;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RecipeFragment extends Fragment implements  RecipeAdapter.RecipeClickOnClickHandler{

    private RecipeAdapter adapter;

    @BindView(R.id.recyclerview_recipe)
    RecyclerView recyclerView;

    @BindString(R.string.intent_detail_put_extra)
    String intentDetail;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this,view);
        load();
        return view;
    }

    private void load(){
        RecipeService service = ServiceFactory.create(RecipeService.class,RecipeService.ENDPOINT);
        Observable<ArrayList<Recipe>> observable = service.getRecipes();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                    System.out.println("TAMANHO----------------------"+recipes.size());
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    adapter = new RecipeAdapter(this);
                    adapter.setRecipeData(recipes);
                    recyclerView.setAdapter(adapter);
                });
    }

    @Override
    public void onClick(Recipe recipe) {

        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra(intentDetail, recipe);
        startActivity(intent);
    }
}
