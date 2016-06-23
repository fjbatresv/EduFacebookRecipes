package edu.galileo.android.facebookrecipes.api;

import java.util.List;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public class RecipeSearchResponse {
    private int count;
    private List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe getFirstRecipe(){
        Recipe recipe = null;
        if (!recipes.isEmpty()){
            recipe = recipes.get(0);
        }
        return recipe;
    }
}
