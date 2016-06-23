package edu.galileo.android.facebookrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by javie on 21/06/2016.
 */
@Database(name = RecipesDatabase.name, version = RecipesDatabase.version)
public class RecipesDatabase {
    public static final int version = 1;
    public static final String name = "Recipes";
}
