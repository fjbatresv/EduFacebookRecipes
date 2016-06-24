package edu.galileo.android.facebookrecipes.list.DI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.facebookrecipes.api.RecipeClient;
import edu.galileo.android.facebookrecipes.api.RecipeService;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.list.RecipeListInteractor;
import edu.galileo.android.facebookrecipes.list.RecipeListInteractorImplementation;
import edu.galileo.android.facebookrecipes.list.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.list.RecipeListPresenterImplementation;
import edu.galileo.android.facebookrecipes.list.RecipeListRepository;
import edu.galileo.android.facebookrecipes.list.RecipeListRepositoryImplementation;
import edu.galileo.android.facebookrecipes.list.StoredRecipeInteractorImplementation;
import edu.galileo.android.facebookrecipes.list.StoredRecipesInteractor;
import edu.galileo.android.facebookrecipes.list.ui.RecipeListView;
import edu.galileo.android.facebookrecipes.list.ui.adapters.OnItemClickListener;
import edu.galileo.android.facebookrecipes.list.ui.adapters.RecipesAdapter;

/**
 * Created by javie on 23/06/2016.
 */
@Module
public class RecipeListModule {
    RecipeListView view;
    OnItemClickListener listener;

    public RecipeListModule(RecipeListView view, OnItemClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    RecipeListView providesRecipeListView(){
        return this.view;
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.listener;
    }

    @Provides
    @Singleton
    RecipesAdapter providesRecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener listener){
        return new RecipesAdapter(recipeList, imageLoader, listener);
    }

    @Provides
    @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus bus, RecipeListView view, RecipeListInteractor interactor, StoredRecipesInteractor store){
        return new RecipeListPresenterImplementation(bus, view, interactor, store);
    }

    @Provides
    @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repo){
        return new RecipeListInteractorImplementation(repo);
    }

    @Provides
    @Singleton
    StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repo){
        return new StoredRecipeInteractorImplementation(repo);
    }

    @Provides
    @Singleton
    RecipeListRepository providesRecipeListRepository(EventBus bus, RecipeService service){
        return new RecipeListRepositoryImplementation(bus);
    }

    @Provides
    @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }

    @Provides
    @Singleton
    List<Recipe> providesEmptyList(){
        return new ArrayList<Recipe>();
    }
}
