package edu.galileo.android.facebookrecipes.main;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;


import java.util.Random;

import edu.galileo.android.facebookrecipes.BuildConfig;
import edu.galileo.android.facebookrecipes.api.RecipeSearchResponse;
import edu.galileo.android.facebookrecipes.api.RecipeService;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.main.events.RecipeMainEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by javie on 23/06/2016.
 */
public class RecipeMainRepositoryImplementation implements RecipeMainRepository {
    private int recipePage;
    private EventBus bus;
    private RecipeService service;


    public RecipeMainRepositoryImplementation(EventBus bus, RecipeService service) {
        this.bus = bus;
        this.service = service;
    }

    @Override
    public void getNextRecipe() {
        Call<RecipeSearchResponse> call = service.search(
                BuildConfig.FOOD_API_KEY,
                RecipeMainRepository.recentSort,
                RecipeMainRepository.count,
                this.recipePage);
        //Llamada asincrona
        Callback<RecipeSearchResponse> callback = new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.isSuccess()){
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if(recipeSearchResponse.getCount() == 0){
                        //El API no lo encuentra, no esta en el rango
                        setRecipePage(new Random().nextInt(RecipeMainRepository.recipeRange));
                        getNextRecipe();
                    }else{
                        Recipe newRecipe = recipeSearchResponse.getFirstRecipe();
                        if(newRecipe != null){
                            Log.e("Repository", "Recipe lista");
                            post(RecipeMainEvent.nextEvent, null, newRecipe);
                        }else{
                            post(RecipeMainEvent.nextEvent, response.message(), null);
                        }
                    }
                }else{
                    post(RecipeMainEvent.nextEvent, response.message(), null);
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                post(RecipeMainEvent.nextEvent, t.getLocalizedMessage(), null);
            }
        };
        call.enqueue(callback);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
        post(RecipeMainEvent.saveEvent, null, recipe);
    }

    @Override
    public void setRecipePage(int recipePage) {
        this.recipePage = recipePage;
    }

    public void post(int type, String error, Recipe recipe){
        bus.post(new RecipeMainEvent(type, error, recipe));
    }
}
