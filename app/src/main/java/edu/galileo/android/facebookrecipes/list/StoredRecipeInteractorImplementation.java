package edu.galileo.android.facebookrecipes.list;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 23/06/2016.
 */
public class StoredRecipeInteractorImplementation implements StoredRecipesInteractor {
    private RecipeListRepository repo;

    public StoredRecipeInteractorImplementation(RecipeListRepository repo) {
        this.repo = repo;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        repo.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        repo.removeRecipe(recipe);
    }
}
