package edu.galileo.android.facebookrecipes.main.ui;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public interface RecipeMainView {
    void handleProgressBar(boolean mostrar);
    void handleUiElements(boolean mostrar);
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);
    void getRecipeError(String error);

}
