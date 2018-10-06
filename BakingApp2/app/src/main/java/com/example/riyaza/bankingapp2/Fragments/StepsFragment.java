package com.example.riyaza.bankingapp2.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.riyaza.bankingapp2.Adapters.StepsAdapter;
import com.example.riyaza.bankingapp2.Fragments.FragmentVideo;
import com.example.riyaza.bankingapp2.R;
import com.example.riyaza.bankingapp2.RecipeDetailsActivity;
import com.example.riyaza.bankingapp2.VideoActivity;
import com.example.riyaza.bankingapp2.model.Recipe;
import com.example.riyaza.bankingapp2.model.Steps;

import java.util.ArrayList;

public class StepsFragment extends Fragment implements StepsAdapter.ListItemClickListner {
    ArrayList<Steps> steps= new ArrayList<Steps>();
    private StepsAdapter mAdapter;
    private RecyclerView mStepsList;
    Recipe recipe;


     public StepsFragment(){

     }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        mStepsList = (RecyclerView)rootView.findViewById(R.id.rvSteps);
        mStepsList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mStepsList.setHasFixedSize(true);

        recipe = getArguments().getParcelable("Recipe");

        steps = recipe.getSteps();
        mAdapter= new StepsAdapter(steps,this);
        mStepsList.setAdapter(mAdapter);



        return rootView;
    }


    @Override
    public void onListItemClick(Steps steps) {

         boolean twoPane= RecipeDetailsActivity.getPane();
     if (!twoPane) {
         Intent intent = new Intent(getActivity().getApplicationContext(), VideoActivity.class);
         Bundle bundle = new Bundle();
         bundle.putParcelable("Steps", steps);
         intent.putExtra("Steps", bundle);
         startActivity(intent);
     }
     else{
         FragmentVideo fragmentVideo= new FragmentVideo();
         FragmentManager fragmentManager3= this.getFragmentManager();
         Bundle VideoBundle = new Bundle();
         VideoBundle.putParcelable("Steps", steps);
         fragmentVideo.setArguments(VideoBundle);
         fragmentManager3.beginTransaction()
                 .replace(R.id.video_container, fragmentVideo)
                 .commit();
         Toast.makeText(this.getActivity(),"Two pane ",Toast.LENGTH_LONG).show();
     }


    }

    @Override
    public void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        if(recipe !=null) {
            outState.putParcelable ("Recipe Array", recipe);
            recipe.getName();
        }



    }
}
