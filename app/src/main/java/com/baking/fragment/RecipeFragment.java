package com.baking.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baking.R;
import com.baking.activity.RecipeDetailsActivity;
import com.baking.adapter.RecipeAdapter;
import com.baking.model.Recipe;
import com.baking.service.RecipeService;
import com.baking.service.ServiceFactory;

import java.util.ArrayList;

import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RecipeFragment extends Fragment implements  RecipeAdapter.RecipeClickOnClickHandler{

    @BindView(R.id.recyclerview_recipe)
    RecyclerView recyclerView;
    @BindView(R.id.error_message_display)
    TextView mErrorMessageDisplay;

    @BindString(R.string.intent_detail_put_extra)
    String intentDetail;

    @BindBool(R.bool.two_pane_mode)
    boolean twoPaneMode;

    private Unbinder unbinder;
    private RecipeAdapter adapter;

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
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isOnline()) {
            load();
        } else {
            showErrorMessage();
        }
    }

    private void load(){
        RecipeService service = ServiceFactory.create(RecipeService.class,RecipeService.ENDPOINT);
        Observable<ArrayList<Recipe>> observable = service.getRecipes();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {

                    if(twoPaneMode){
                        adapter = new RecipeAdapter(this);
                        adapter.setRecipeData(recipes);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                    }else{
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new RecipeAdapter(this);
                        adapter.setRecipeData(recipes);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra(intentDetail, recipe);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void showErrorMessage() {
        recyclerView.setVisibility(View.GONE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

}
