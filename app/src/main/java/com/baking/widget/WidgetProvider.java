package com.baking.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.baking.R;
import com.baking.model.Ingredient;
import com.baking.model.Recipe;

/**
 * Created by vinicius.rocha on 2/13/18.
 */

public class WidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Recipe recipe) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list);
        views.setTextViewText(R.id.w_recipe_name, recipe.name());
        views.removeAllViews(R.id.w_ingredients);

        for (Ingredient ingredient : recipe.ingredients()) {
            StringBuilder sb = new StringBuilder();
            RemoteViews ingredientView = new RemoteViews(context.getPackageName(),
                    R.layout.widget_list_item);

            String name = ingredient.ingredient();
            float quantity = ingredient.quantity();
            String measure = ingredient.measure();
            sb.append(name +" - "+quantity+" - "+measure);

            ingredientView.setTextViewText(R.id.widget_ingredient_name, sb);
            views.addView(R.id.w_ingredients, ingredientView);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
