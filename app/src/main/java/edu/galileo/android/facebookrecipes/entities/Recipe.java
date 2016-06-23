package edu.galileo.android.facebookrecipes.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import edu.galileo.android.facebookrecipes.db.RecipesDatabase;

/**
 * Created by javie on 21/06/2016.
 */
@Table(database = RecipesDatabase.class)
public class Recipe extends BaseModel{
    @PrimaryKey
    @SerializedName("recipe_id")
    private String recipeId;

    @Column
    private String title;

    @Column
    @SerializedName("image_url")
    private String imageUrl;

    @Column
    @SerializedName("source_url")
    private String sourceUrl;

    @Column
    private boolean favorite;

    public Recipe() {
    }

    public Recipe(String recipeId, String title, String imageUrl, String sourceUrl, boolean favorite) {
        this.recipeId = recipeId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.favorite = favorite;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        boolean respuesta = false;
        if(o instanceof Recipe){
            Recipe recipe = (Recipe) o;
            respuesta = this.recipeId.equals(recipe.getRecipeId());
        }
        return respuesta;
    }
}
