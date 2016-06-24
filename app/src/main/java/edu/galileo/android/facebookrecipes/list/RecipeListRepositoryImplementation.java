package edu.galileo.android.facebookrecipes.list;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.runtime.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.Arrays;
import java.util.List;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.entities.Recipe_Table;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.list.events.RecipeListEvent;

/**
 * Created by javie on 23/06/2016.
 */
public class RecipeListRepositoryImplementation implements RecipeListRepository {
    private EventBus bus;

    public RecipeListRepositoryImplementation(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void getSavedRecipes() {
        //Params: cache de la data, tipo de dato o tabla
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(false, Recipe.class);
        post(RecipeListEvent.read, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void getFabRecipes() {
        //Params: cache de la data, tipo de dato o tabla
        List<Recipe> fabRecipes = SQLite.select()
                .from(Recipe.class)
                .where(Recipe_Table.favorite.eq(true))
                .queryList();
        post(RecipeListEvent.read, fabRecipes);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post(RecipeListEvent.update, null);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.delete, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipeList){
        bus.post(new RecipeListEvent(type, recipeList));
    }
}
