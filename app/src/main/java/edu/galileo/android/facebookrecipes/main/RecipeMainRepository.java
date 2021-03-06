package edu.galileo.android.facebookrecipes.main;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public interface RecipeMainRepository {
    public final static int count = 1;
    public final static int recipeRange = 100000;
    public final static String recentSort = "r";

    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePage(int recipePage);
}
