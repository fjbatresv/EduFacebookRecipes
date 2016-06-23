package edu.galileo.android.facebookrecipes.main;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.main.events.RecipeMainEvent;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainView;

/**
 * Created by javie on 22/06/2016.
 */
public interface RecipeMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    void imageError(String error);
    void imageReady();

    RecipeMainView getView();
}
