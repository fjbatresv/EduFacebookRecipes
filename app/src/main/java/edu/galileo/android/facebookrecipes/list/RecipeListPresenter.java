package edu.galileo.android.facebookrecipes.list;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.list.events.RecipeListEvent;
import edu.galileo.android.facebookrecipes.list.ui.RecipeListView;

/**
 * Created by javie on 22/06/2016.
 */
public interface RecipeListPresenter {
    void onCreate();
    void onDestroy();
    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);
    RecipeListView getView();
}
