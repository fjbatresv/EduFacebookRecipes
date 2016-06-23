package edu.galileo.android.facebookrecipes;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

import edu.galileo.android.facebookrecipes.libs.DI.LibsModule;
import edu.galileo.android.facebookrecipes.login.ui.LoginActivity;
import edu.galileo.android.facebookrecipes.main.DI.DaggerRecipeMainComponent;
import edu.galileo.android.facebookrecipes.main.DI.RecipeMainComponent;
import edu.galileo.android.facebookrecipes.main.DI.RecipeMainModule;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainActivity;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainView;

/**
 * Created by javie on 21/06/2016.
 */
public class FacebookRecipesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
    }

    public void logout(){
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, LoginActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
        ));
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view){
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }
}
