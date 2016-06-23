package edu.galileo.android.facebookrecipes.main.DI;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.facebookrecipes.libs.DI.LibsModule;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.main.RecipeMainPresenter;

/**
 * Created by javie on 23/06/2016.
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    ImageLoader providesImageLoader();
    RecipeMainPresenter getRecipeMainPresenter();

}
