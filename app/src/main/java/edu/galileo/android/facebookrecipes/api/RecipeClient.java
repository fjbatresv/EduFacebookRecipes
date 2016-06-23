package edu.galileo.android.facebookrecipes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by javie on 22/06/2016.
 */
public class RecipeClient {
    private Retrofit retrofit;
    private final static String baseUrl = "http://food2fork.com/api/";

    public RecipeClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public RecipeService getRecipeService(){
        return this.retrofit.create(RecipeService.class);
    }
}
