package com.baking.resource;

import com.baking.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vinicius.rocha on 2/3/18.
 */

public interface RecipeResource {

    String ENDPOINT = "https://d17h27t6h515a5.cloudfront.net/";

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Recipe>> getRecipes();
}
