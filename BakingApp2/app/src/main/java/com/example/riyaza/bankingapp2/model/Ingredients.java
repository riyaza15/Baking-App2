package com.example.riyaza.bankingapp2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {


    private String ingredientQuantity;
    private String ingredientMeasure;
    private String ingredientName;


    public Ingredients(String ingredientQuantity, String ingredientMeasure, String ingredientName) {
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientName = ingredientName;
    }

    protected Ingredients(Parcel in) {
        ingredientQuantity = in.readString();
        ingredientMeasure = in.readString();
        ingredientName = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ingredientQuantity);
        parcel.writeString(ingredientMeasure);
        parcel.writeString(ingredientName);
    }
}
