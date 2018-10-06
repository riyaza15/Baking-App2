package com.example.riyaza.bankingapp2;


import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RemoteViews;

import com.example.riyaza.bankingapp2.Adapters.RecipeAdapter;
import com.example.riyaza.bankingapp2.model.Ingredients;
import com.example.riyaza.bankingapp2.model.Recipe;

import java.util.ArrayList;
import java.util.List;



public class WidgetActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListner {

    ArrayList<Recipe> recipes= new ArrayList<Recipe>();
    private RecipeAdapter mAdapter;
    private RecyclerView mRecipeList;
    private Parcelable mListState = null;
    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private AppWidgetManager widgetManager;
    private RemoteViews views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeList = (RecyclerView)findViewById(R.id.rvRecipeList);
        //  mRecipeList.setLayoutManager(new GridLayoutManager(getActivity(), getGridCol()));
        GridLayoutManager layoutManager = new GridLayoutManager(this, getGridCol());
        mRecipeList.setLayoutManager(layoutManager);

        mRecipeList.setHasFixedSize(true);
        mAdapter = new RecipeAdapter(recipes,this);
        mRecipeList.setAdapter(mAdapter);
       RecipeAsyncTask task = new RecipeAsyncTask();
        task.execute(getString(R.string.url));
        widgetManager = AppWidgetManager.getInstance(this);
        views = new RemoteViews(this.getPackageName(), R.layout.widget_provider);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
    }
    private int getGridCol() {
        int size = 1;
        switch(getResources().getConfiguration().orientation){
            case Configuration.ORIENTATION_PORTRAIT:
                size= 2;
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                size= 3;
                break;
        }
        return size;
    }

    @Override
    public void onListItemClick(Recipe recipe) {
        views.setTextViewText(R.id.wigdet_title, recipe.getName());
        List<Ingredients> ingredients = recipe.getIngredients();
        StringBuilder ing= new StringBuilder();
        for (Ingredients ingredient: ingredients){
            ing.append("- ")
                    .append(ingredient.getIngredientName())
                    .append(" ")
                    .append(ingredient.getIngredientMeasure())
                    .append(" ")
                    .append(ingredient.getIngredientQuantity())
                    .append("\n");
        }

        views.setTextViewText(R.id.baking_widget_ingredients, ing.toString());

        widgetManager.updateAppWidget(mAppWidgetId, views);
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();




    }

    public  class RecipeAsyncTask extends AsyncTask<String, Void, List<Recipe>> {
        @Override
        public List<Recipe> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Recipe> result = QueryUtils.fetchRecipeData(urls[0]);
            return result;
        }



        @Override
        public void onPostExecute(List<Recipe> data) {

            if (data != null && !data.isEmpty()) {
                mAdapter.setRecipeData(data);
                // movies= data;
                recipes.addAll(data);

            }

        }
    }
}