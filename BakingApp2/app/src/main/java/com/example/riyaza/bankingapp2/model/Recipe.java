package com.example.riyaza.bankingapp2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe extends ArrayList<Parcelable> implements Parcelable {

    private int id;
    private String name;
    private String servings;
    private String image;
    private ArrayList<Ingredients> ingredients;
    private ArrayList<Steps> steps;

    public Recipe(int id, String name, String servings, String image, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps) {
        this.id=id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }


    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readString();
        image = in.readString();
        ingredients = in.createTypedArrayList(Ingredients.CREATOR);
        steps = in.createTypedArrayList(Steps.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeString(image);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
    }

}
