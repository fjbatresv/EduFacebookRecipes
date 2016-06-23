package edu.galileo.android.facebookrecipes.list.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.list.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.list.events.RecipeListEvent;
import edu.galileo.android.facebookrecipes.list.ui.adapters.RecipesAdapter;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.mainRecyclerView)
    RecyclerView recyclerView;

    RecipesAdapter adapter;
    RecipeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
        setUpInjection();
        presenter.onCreate();
        presenter.getRecipes();
    }

    private void setUpInjection() {
        presenter = new RecipeListPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void getRecipes() {

            }

            @Override
            public void removeRecipe(Recipe recipe) {

            }

            @Override
            public void toggleFavorite(Recipe recipe) {

            }

            @Override
            public void onEventMainThread(RecipeListEvent event) {

            }

            @Override
            public RecipeListView getView() {
                return null;
            }
        };
    }

    private void setupRecyclerView() {

    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }
    @OnClick(R.id.toolbar)
    public void onToolbarClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setRecipes(List<Recipe> data) {

    }

    @Override
    public void recipeUpdated() {

    }

    @Override
    public void recipeDeleted(Recipe recipe) {

    }
}
