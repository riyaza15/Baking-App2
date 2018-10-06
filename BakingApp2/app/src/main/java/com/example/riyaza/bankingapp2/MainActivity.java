package com.example.riyaza.bankingapp2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.riyaza.bankingapp2.Adapters.RecipeAdapter;
import com.example.riyaza.bankingapp2.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListner{
    public static final String LOG_TAG = MainActivity.class.getName();
    ArrayList<Recipe> recipes= new ArrayList<Recipe>();
    private RecipeAdapter mAdapter;
    private RecyclerView mRecipeList;

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
        Intent intent = new Intent(MainActivity.this, RecipeDetailsActivity.class);
         intent.putExtra("Recipe", (Parcelable) recipe);

        startActivity(intent);




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

