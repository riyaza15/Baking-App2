package com.example.riyaza.bankingapp2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.riyaza.bankingapp2.R;
import com.example.riyaza.bankingapp2.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class IncredientAdapter extends RecyclerView.Adapter<IncredientAdapter.IncredientViewHolder> {

    ArrayList<Ingredients> ingredients;

    public IncredientAdapter(ArrayList<Ingredients> ingredients){
        this.ingredients= ingredients;
    }



    @NonNull
    @Override
    public IncredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        IncredientAdapter.IncredientViewHolder viewHolder = new IncredientAdapter.IncredientViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IncredientViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IncredientViewHolder extends RecyclerView.ViewHolder {
        TextView  txtIngredientName;
        TextView  txtMesure;
        TextView  txtQuantity;



        public IncredientViewHolder(View itemView) {
            super(itemView);
            txtIngredientName=(TextView) itemView.findViewById(R.id.ingredient_name);
            txtMesure= (TextView)itemView.findViewById(R.id.ingredient_measure) ;
            txtQuantity=(TextView)itemView.findViewById(R.id.ingredient_quantity) ;

        }

        void bind( int listIndex) {
         Ingredients currentIngredient= ingredients.get(listIndex)  ;
         String ingredientName= currentIngredient.getIngredientName();
         String ingredientMesure=currentIngredient.getIngredientMeasure();
         String ingredientQuantity= currentIngredient.getIngredientQuantity();

         txtIngredientName.setText(ingredientName);
         txtMesure.setText(ingredientMesure);
         txtQuantity.setText(ingredientQuantity);


        }
    }
    public void setRecipeData(List<Ingredients> ingredientsData) {
        ingredients = (ArrayList<Ingredients>) ingredientsData;
        notifyDataSetChanged();
    }


}
