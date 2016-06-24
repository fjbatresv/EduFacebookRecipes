package edu.galileo.android.facebookrecipes.list;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public interface RecipeListRepository {
    void getSavedRecipes();
    void getFabRecipes();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
}
