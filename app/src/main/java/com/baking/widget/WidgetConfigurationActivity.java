package com.baking.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.RadioGroup;

import com.baking.R;
import com.baking.model.Recipe;
import com.baking.service.RecipeService;
import com.baking.service.ServiceFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WidgetConfigurationActivity extends AppCompatActivity {

    @BindView(R.id.radioGroup)
    RadioGroup namesRadioGroup;

    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private ArrayList<Recipe> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_configuration);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                finish();
            }
        }

        if(isOnline()){
            RecipeService service = ServiceFactory.create(RecipeService.class,RecipeService.ENDPOINT);
            Observable<ArrayList<Recipe>> observable = service.getRecipes();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(recipes -> {
                            this.recipes = recipes;
                        if(recipes.isEmpty()){
                            finish();
                        } else {
                            // Fill the radioGroup
                            int currentIndex = 0;

                            for (Recipe recipe : recipes) {
                                AppCompatRadioButton button = new AppCompatRadioButton(this);
                                button.setText(recipe.name());
                                button.setId(currentIndex++);
                                namesRadioGroup.addView(button);
                            }

                            // Check the first item when loaded
                            if (namesRadioGroup.getChildCount() > 0) {
                                ((AppCompatRadioButton) namesRadioGroup.getChildAt(0)).setChecked(true);
                            }
                        }
                    });
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @OnClick(R.id.button)
    public void onOkButtonClick() {
        int checkedItemId = namesRadioGroup.getCheckedRadioButtonId();
        Recipe recipe = recipes.get(checkedItemId);

        WidgetProvider.updateAppWidget(getApplicationContext(),AppWidgetManager.getInstance(getApplicationContext()), mAppWidgetId, recipe);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

}
