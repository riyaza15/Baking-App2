package com.example.riyaza.bankingapp2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.riyaza.bankingapp2.R;
import com.example.riyaza.bankingapp2.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.example.riyaza.bankingapp2.R.id.nutella_pie;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>  {

    final private ListItemClickListner mOnclickListner;
    ArrayList<Recipe> recipes;

    public RecipeAdapter(ArrayList<Recipe> recipes,ListItemClickListner mOnclickListner) {
        this.recipes = recipes;
        this.mOnclickListner=mOnclickListner;
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        RecipeAdapter.RecipeViewHolder viewHolder = new RecipeAdapter.RecipeViewHolder(view);

        return viewHolder;
    }

    public interface ListItemClickListner{
        void onListItemClick(Recipe recipe);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
       return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipeItemView;
        ImageView recipeImageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeItemView=(TextView) itemView.findViewById(R.id.itemName);
            recipeImageView=(ImageView)itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);


        }

        @SuppressLint("ResourceType")
        void bind(int listIndex) {

            Recipe currentRecipe = recipes.get(listIndex);
            String name= currentRecipe.getName();
            String url= currentRecipe.getImage();
           if (name.equals("Nutella Pie")) {
            Picasso.get().load(R.drawable.nutella_pie).into(recipeImageView);

            }
            else if (name.equals("Brownies")){
               Picasso.get().load(R.drawable.brownies).into(recipeImageView);
           }
           else if (name.equals("Yellow Cake")){
               Picasso.get().load(R.drawable.yellow_cake).into(recipeImageView);
           }
           else if (name.equals("Cheesecake")){
               Picasso.get().load(R.drawable.cheescake).into(recipeImageView);
           }


            recipeItemView.setText(name);

        }

        @Override
        public void onClick(View view) {
            int clickPosition= getAdapterPosition();
            mOnclickListner.onListItemClick(recipes.get(clickPosition));
        }
    }


    public void setRecipeData(List<Recipe> recipeData) {
        recipes = (ArrayList<Recipe>) recipeData;
        notifyDataSetChanged();
    }
}
