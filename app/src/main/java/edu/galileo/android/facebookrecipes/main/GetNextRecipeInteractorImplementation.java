package edu.galileo.android.facebookrecipes.main;

import java.util.Random;

/**
 * Created by javie on 23/06/2016.
 */
public class GetNextRecipeInteractorImplementation implements GetNextRecipeInteractor {
    private RecipeMainRepository repo;

    public GetNextRecipeInteractorImplementation(RecipeMainRepository repo) {
        this.repo = repo;
    }

    @Override
    public void execute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.recipeRange);
        repo.setRecipePage(recipePage);
        repo.getNextRecipe();
    }
}
