package edu.galileo.android.facebookrecipes.list;

/**
 * Created by javie on 23/06/2016.
 */
public class RecipeListInteractorImplementation implements RecipeListInteractor {
    private RecipeListRepository repo;

    public RecipeListInteractorImplementation(RecipeListRepository repo) {
        this.repo = repo;
    }

    @Override
    public void execute() {
        repo.getSavedRecipes();
    }

    @Override
    public void fabs() {
        repo.getFabRecipes();
    }
}
