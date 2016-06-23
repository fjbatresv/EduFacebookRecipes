package edu.galileo.android.facebookrecipes.main;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 23/06/2016.
 */
public class SaveRecipeInteractorImplementation implements SaveRecipeInteractor {
    private RecipeMainRepository repo;

    public SaveRecipeInteractorImplementation(RecipeMainRepository repo) {
        this.repo = repo;
    }

    @Override
    public void execute(Recipe recipe) {
        repo.saveRecipe(recipe);
    }
}
