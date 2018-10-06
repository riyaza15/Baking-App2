package com.example.riyaza.bankingapp2;

import android.text.TextUtils;
import android.util.Log;


import com.example.riyaza.bankingapp2.model.Ingredients;
import com.example.riyaza.bankingapp2.model.Recipe;
import com.example.riyaza.bankingapp2.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//import static com.example.riyaza.bakingapp.RecipeListFragment.LOG_TAG;

public class QueryUtils {
    //Tutorial followed Android Basic Networking course


    private QueryUtils(){
    }
    public static List<Recipe> fetchRecipeData(String requestUrl ) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("", "Problem creating the HTTP request.", e);
        }
        List<Recipe> recipes = extractFeatureFromJson(jsonResponse);
        return recipes;
    }



    private static List<Recipe> extractFeatureFromJson(String recipeJSON) {
        if (TextUtils.isEmpty(recipeJSON)) {
            return null;
        }
        List<Recipe> recipes = new ArrayList<>();
        try {
            JSONArray recipeArray = new JSONArray(recipeJSON);
            ;
            for (int i = 0; i < recipeArray.length(); i++) {
                JSONObject currentRecipe = recipeArray.getJSONObject(i);
                int recipeId   = currentRecipe.getInt("id");
                String name = currentRecipe.getString("name");
                String servings=currentRecipe.getString("servings");
                String image=currentRecipe.getString("image");

                ArrayList<Ingredients> ingredients = new ArrayList<>();
                JSONArray ingredientsArray = currentRecipe.getJSONArray("ingredients");

                for (int n = 0; n < ingredientsArray.length(); n++) {
                    JSONObject currentIngredient = ingredientsArray.getJSONObject(n);
                    String ingredientQuantity = currentIngredient.getString("quantity");
                    String ingredientMeasure = currentIngredient.getString("measure");
                    String ingredientName = currentIngredient.getString("ingredient");
                    Ingredients ingredient = new Ingredients(ingredientQuantity, ingredientMeasure, ingredientName);
                    ingredients.add(ingredient);
                }

                ArrayList<Steps> steps = new ArrayList<>();
                JSONArray stepsArray = currentRecipe.getJSONArray("steps");

                for (int a = 0; a < stepsArray.length(); a++) {
                    JSONObject currentStep = stepsArray.getJSONObject(a);
                    String stepId = currentStep.getString("id");
                    String stepShortDescription = currentStep.getString("shortDescription");
                    String stepDescription = currentStep.getString("description");
                    String videoURL = currentStep.getString("videoURL");
                    String thumbnailURL = currentStep.getString("thumbnailURL");
                    Steps step = new Steps(stepId, stepShortDescription, stepDescription, videoURL, thumbnailURL);
                    steps.add(step);

                }
                Recipe recipe = new Recipe(recipeId,name, servings,image, ingredients, steps);
                recipes.add(recipe);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem in the Recipe JSON results", e);
        }
        return recipes;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("", "Problem building the URL ", e);
        }
        Log.v("URL", String.valueOf(url));
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e("", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
                Log.i("Detail", "connected "+ line);

            }
        }
        return output.toString();
    }
}
