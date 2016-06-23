package edu.galileo.android.facebookrecipes.main.DI;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.facebookrecipes.api.RecipeClient;
import edu.galileo.android.facebookrecipes.api.RecipeService;
import edu.galileo.android.facebookrecipes.main.GetNextRecipeInteractor;
import edu.galileo.android.facebookrecipes.main.GetNextRecipeInteractorImplementation;
import edu.galileo.android.facebookrecipes.main.RecipeMainPresenter;
import edu.galileo.android.facebookrecipes.main.RecipeMainPresenterImplementation;
import edu.galileo.android.facebookrecipes.main.RecipeMainRepository;
import edu.galileo.android.facebookrecipes.main.RecipeMainRepositoryImplementation;
import edu.galileo.android.facebookrecipes.main.SaveRecipeInteractor;
import edu.galileo.android.facebookrecipes.main.SaveRecipeInteractorImplementation;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainView;

/**
 * Created by javie on 23/06/2016.
 */
@Module
public class RecipeMainModule {
    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeMainView providesRecipeMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(RecipeMainView view, EventBus bus, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor){
        return new RecipeMainPresenterImplementation(view, bus, saveRecipeInteractor, getNextRecipeInteractor);
    }

    @Provides
    @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repo){
        return new SaveRecipeInteractorImplementation(repo);
    }

    @Provides
    @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repo){
        return new GetNextRecipeInteractorImplementation(repo);
    }

    @Provides
    @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus bus, RecipeService service){
        return new RecipeMainRepositoryImplementation(bus, service);
    }

    @Provides
    @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }
}
