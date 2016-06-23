package edu.galileo.android.facebookrecipes.main;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.main.events.RecipeMainEvent;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainView;

/**
 * Created by javie on 23/06/2016.
 */
public class RecipeMainPresenterImplementation implements RecipeMainPresenter {
    private RecipeMainView view;
    private EventBus bus;
    private SaveRecipeInteractor saveRecipeInteractor;
    private GetNextRecipeInteractor getNextRecipeInteractor;

    public RecipeMainPresenterImplementation(RecipeMainView view, EventBus bus, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor) {
        this.view = view;
        this.bus = bus;
        this.saveRecipeInteractor = saveRecipeInteractor;
        this.getNextRecipeInteractor = getNextRecipeInteractor;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        bus.unregister(this);
    }

    @Override
    public void dismissRecipe() {
        if(view != null){
            view.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if (view != null){
            view.handleUiElements(false);
            view.handleProgressBar(true);
        }
        getNextRecipeInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if (view != null){
            view.saveAnimation();
            view.handleUiElements(false);
            view.handleProgressBar(true);
        }
        saveRecipeInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if (view != null){
            String error = event.getError();
            if(error != null){
                view.handleProgressBar(false);
                view.getRecipeError(error);
            }else{
                switch (event.getType()){
                    case RecipeMainEvent.nextEvent:
                        Log.e("Presenter", "Recipe lista");
                        view.setRecipe(event.getRecipe());
                        break;
                    case RecipeMainEvent.saveEvent:
                        view.onRecipeSaved();
                        getNextRecipeInteractor.execute();
                        break;
                }
            }
        }
    }

    @Override
    public void imageError(String error) {
        if (view != null){
            view.getRecipeError(error);
        }
    }

    @Override
    public void imageReady() {
        if (view != null){
            view.handleProgressBar(false);
            view.handleUiElements(true);
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }
}
