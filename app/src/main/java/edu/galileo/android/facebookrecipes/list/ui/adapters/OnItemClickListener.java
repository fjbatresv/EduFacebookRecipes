package edu.galileo.android.facebookrecipes.list.ui.adapters;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by javie on 22/06/2016.
 */
public interface OnItemClickListener {
    void onFavoriteClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
    void onItemClick(Recipe recipe);
}
