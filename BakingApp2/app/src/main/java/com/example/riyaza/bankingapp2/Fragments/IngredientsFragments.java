package com.example.riyaza.bankingapp2.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.riyaza.bankingapp2.Adapters.IncredientAdapter;
import com.example.riyaza.bankingapp2.R;
import com.example.riyaza.bankingapp2.model.Ingredients;
import com.example.riyaza.bankingapp2.model.Recipe;

import java.util.ArrayList;

public class IngredientsFragments extends Fragment {

    ArrayList<Ingredients> ingredients= new ArrayList<Ingredients>();
    private IncredientAdapter mAdapter;
    private RecyclerView mIncredientsList;
    Recipe recipe;


    public IngredientsFragments(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        mIncredientsList = (RecyclerView)rootView.findViewById(R.id.rvIngredients);
        mIncredientsList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mIncredientsList.setHasFixedSize(true);

        recipe = getArguments().getParcelable("Recipe");

        ingredients = recipe.getIngredients();
        mAdapter= new IncredientAdapter(ingredients);
        mIncredientsList.setAdapter(mAdapter);



        return rootView;
    }




}
