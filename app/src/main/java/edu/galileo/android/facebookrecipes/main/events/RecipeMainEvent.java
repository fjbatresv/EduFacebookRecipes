package edu.galileo.android.facebookrecipes.main.events;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public class RecipeMainEvent {
    private int type;
    private String error;
    private Recipe recipe;

    public final static int nextEvent = 0;
    public final static int saveEvent = 1;

    public RecipeMainEvent() {
    }

    public RecipeMainEvent(int type, String error, Recipe recipe) {
        this.type = type;
        this.error = error;
        this.recipe = recipe;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
