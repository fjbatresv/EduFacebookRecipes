package edu.galileo.android.facebookrecipes.list.events;

import java.util.List;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public class RecipeListEvent {
    private int type;
    private List<Recipe> recipeList;

    public final static int read = 0;
    public final static int update = 1;
    public final static int delete = 2;

    public RecipeListEvent() {
    }

    public RecipeListEvent(int type, List<Recipe> recipeList) {
        this.type = type;
        this.recipeList = recipeList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
