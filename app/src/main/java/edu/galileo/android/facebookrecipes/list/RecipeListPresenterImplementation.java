package edu.galileo.android.facebookrecipes.list;


import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.list.events.RecipeListEvent;
import edu.galileo.android.facebookrecipes.list.ui.RecipeListView;

/**
 * Created by javie on 23/06/2016.
 */
public class RecipeListPresenterImplementation implements RecipeListPresenter {
    private EventBus bus;
    private RecipeListView view;
    private RecipeListInteractor interactor;
    private StoredRecipesInteractor store;

    public RecipeListPresenterImplementation(EventBus bus, RecipeListView view, RecipeListInteractor interactor, StoredRecipesInteractor store) {
        this.bus = bus;
        this.view = view;
        this.interactor = interactor;
        this.store = store;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        bus.unRegister(this);
        view = null;
    }

    @Override
    public void getRecipes() {
        interactor.execute();
    }

    @Override
    public void getFavorites() {
        interactor.fabs();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        store.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav = recipe.getFavorite();
        recipe.setFavorite(!fav);
        store.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if (view != null){
            switch (event.getType()){
                case RecipeListEvent.read:
                    view.setRecipes(event.getRecipeList());
                    break;
                case RecipeListEvent.update:
                    view.recipeUpdated();
                    break;
                case RecipeListEvent.delete:
                    view.recipeDeleted(event.getRecipeList().get(0));
                    break;
            }
        }
    }

    @Override
    public RecipeListView getView() {
        return this.view;
    }
}
