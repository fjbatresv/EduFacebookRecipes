package edu.galileo.android.facebookrecipes.list.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.facebookrecipes.FacebookRecipesApp;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.GlideImageLoader;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.list.DI.RecipeListComponent;
import edu.galileo.android.facebookrecipes.list.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.list.events.RecipeListEvent;
import edu.galileo.android.facebookrecipes.list.ui.adapters.OnItemClickListener;
import edu.galileo.android.facebookrecipes.list.ui.adapters.RecipesAdapter;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainActivity;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.mainRecyclerView)
    RecyclerView recyclerView;

    private RecipesAdapter adapter;
    private RecipeListPresenter presenter;
    private RecipeListComponent component;
    public final static String useFabs = "fabs";
    private boolean fabs;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        setupToolbar();
        setUpInjection();
        setupRecyclerView();
        presenter.onCreate();
        loadData(getIntent());
    }

    private void loadData(Intent intent) {
        if(intent.getBooleanExtra(this.useFabs, false)){
            showFabs(true);
        }else{
            showFabs(false);
        }
    }

    private void setUpInjection() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        component = app.getRecipeListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.menu_list, menu);
        this.menu = menu;
        loadTitle(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadTitle(Menu menu) {
        MenuItem opt = menu.findItem(R.id.menu_fabs);
        if(this.fabs){
            opt.setTitle(getString(R.string.global_menu_action_all));
        }else{
            opt.setTitle(getString(R.string.global_menu_action_fabs));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                navigateToMainScreen();
                break;
            case R.id.menu_logout:
                logout();
                break;
            case R.id.menu_fabs:
                showFabs();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFabs(boolean fabs) {
        this.fabs = fabs;
        if(fabs){
            presenter.getFavorites();
        }else{
            presenter.getRecipes();
        }
    }

    private void showFabs(){
        MenuItem opt = this.menu.findItem(R.id.menu_fabs);
        showFabs(opt.getTitle().toString() == getString(R.string.global_menu_action_fabs));
        loadTitle(this.menu);
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    @Override
    public void onFavoriteClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceUrl())));
    }

    public RecipeListPresenter getPresenter() {
        return component.getRecipeListPresenter();
    }

    public RecipesAdapter getAdapter() {
        return component.getAdapter();
    }
}
