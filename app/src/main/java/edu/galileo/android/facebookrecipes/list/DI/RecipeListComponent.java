package edu.galileo.android.facebookrecipes.list.DI;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.facebookrecipes.libs.DI.LibsModule;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.list.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.list.ui.adapters.RecipesAdapter;
import edu.galileo.android.facebookrecipes.main.RecipeMainPresenter;

/**
 * Created by javie on 23/06/2016.
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    RecipesAdapter getAdapter();
    RecipeListPresenter getRecipeListPresenter();
}
