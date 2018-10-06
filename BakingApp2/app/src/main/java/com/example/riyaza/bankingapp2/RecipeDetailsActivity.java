package com.example.riyaza.bankingapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.riyaza.bankingapp2.Fragments.FragmentVideo;
import com.example.riyaza.bankingapp2.Fragments.IngredientsFragments;
import com.example.riyaza.bankingapp2.Fragments.StepsFragment;
import com.example.riyaza.bankingapp2.model.Ingredients;
import com.example.riyaza.bankingapp2.model.Recipe;
import com.example.riyaza.bankingapp2.model.Steps;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {
    private static boolean mTwoPane;
    Recipe recipe;
    ArrayList<Ingredients> ingArr= new ArrayList<Ingredients>();
    ArrayList<Steps> steps= new ArrayList<Steps>();

   // private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Toast.makeText(this,"No saved instance",Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            recipe =  intent.getExtras().getParcelable("Recipe");

        } else {
            Log.i("  Instance ","Recipe Not Null");
            recipe =  savedInstanceState.getParcelable("Recipe Array");
            Toast.makeText(this,"Recipe not  null",Toast.LENGTH_LONG).show();
    }
            steps = recipe.getSteps();
            ingArr= recipe.getIngredients();
            int size= ingArr.size();


            IngredientsFragments ingredientsFragments= new IngredientsFragments();
            FragmentManager fragmentManager= getSupportFragmentManager();
            Bundle ingredientsBundle = new Bundle();
            ingredientsBundle.putParcelable("Recipe", recipe);
            ingredientsFragments.setArguments(ingredientsBundle);
            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientsFragments)
                    .commit();
            StepsFragment stepsFragment= new StepsFragment();
            FragmentManager fragmentManager2= getSupportFragmentManager();
            Bundle stepsBundle = new Bundle();
            stepsBundle.putParcelable("Recipe", recipe);
            stepsFragment.setArguments(stepsBundle);
            fragmentManager2.beginTransaction()
                    .add(R.id.steps_container,stepsFragment)
                    .commit();

            if(findViewById(R.id.tableLayout)!=null){

            FragmentVideo fragmentVideo= new FragmentVideo();
            FragmentManager fragmentManager3= getSupportFragmentManager();
            Bundle VideoBundle = new Bundle();
            VideoBundle.putParcelable("Steps", steps.get(0));
            fragmentVideo.setArguments(VideoBundle);
                fragmentManager3.beginTransaction()
                .add(R.id.video_container, fragmentVideo)
                .commit();

     Toast.makeText(this,"Twopane true",Toast.LENGTH_LONG).show();
     mTwoPane=true;

 }
 else {
     mTwoPane = false;
 }
    }


    public static boolean getPane(){
       return mTwoPane;
    }


    @Override
    public void onSaveInstanceState( Bundle outState) {
        if(recipe !=null) {
            outState.putParcelable ("Recipe Array", recipe);
            Toast.makeText(this,"movie not  null",Toast.LENGTH_LONG).show();
            recipe.getName();
        }
        super.onSaveInstanceState(outState);



    }
}
