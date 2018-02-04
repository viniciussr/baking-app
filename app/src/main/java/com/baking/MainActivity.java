package com.baking;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baking.adapter.RecipeAdapter;
import com.baking.model.Recipe;
import com.baking.resource.RecipeResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//

    @BindView(R.id.tv_error_message_display)
    TextView text;
    private ArrayList<Recipe> recipes;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadJson();
    }

    private void initViews() {
//        text.setText("TESTTTTTTTTTTTTT PORAAAAAAAAA");
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        loadJson();
    }

    private void loadJson() {

        text.setText("TESTTTTTTTTTTTTT PORAAAAAAAAA");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecipeResource.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeResource request = retrofit.create(RecipeResource.class);
        Call<ArrayList<Recipe>> call = request.getRecipes();
        call.enqueue(new Callback<ArrayList<Recipe>>() {

//            @BindView(R.id.recyclerview_recipe)
//            RecyclerView recyclerView;


            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {



                System.out.println("TAMANHO----------------------"+response.body().size());
                Toast.makeText(getApplicationContext(),"TAMANHO "+response.body().size(),Toast.LENGTH_LONG).show();



//                recyclerView.setHasFixedSize(true);
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(layoutManager);
//
//                adapter = new RecipeAdapter(response.body());
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                Log.d("Error---------", t.getMessage());
            }
        });

    }

}
