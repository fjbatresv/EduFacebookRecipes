package edu.galileo.android.facebookrecipes.main.ui;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.facebookrecipes.FacebookRecipesApp;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.list.ui.RecipeListActivity;
import edu.galileo.android.facebookrecipes.main.DI.RecipeMainComponent;
import edu.galileo.android.facebookrecipes.main.RecipeMainPresenter;
import edu.galileo.android.facebookrecipes.main.events.RecipeMainEvent;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView, SwipeGestureListener {
    @Bind(R.id.imgRecipe)
    ImageView imgRecipe;
    @Bind(R.id.imgKeep)
    ImageButton imgKeep;
    @Bind(R.id.imgDismiss)
    ImageButton imgDismiss;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutContainer)
    RelativeLayout container;

    private RecipeMainPresenter presenter;
    private Recipe currentRecipe;
    private ImageLoader imageLoader;
    private RecipeMainComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);
        setupInjection();
        //setupImageLoader();
        setupGestureDetector();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    private void setupGestureDetector() {
        final GestureDetector gestureDetector = new GestureDetector(this, new SwypeGestureDetector(this));
        View.OnTouchListener gestOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        imgRecipe.setOnTouchListener(gestOnTouchListener);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                logout();
                break;
            case R.id.action_list:
                navigateToListScreen();
                break;
            case R.id.menu_main_fabs:
                showFabs();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFabs() {
        startActivity(new Intent(this, RecipeListActivity.class)
                .putExtra(RecipeListActivity.useFabs, true)
        );
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, RecipeListActivity.class));
    }

    private void logout() {
        Snackbar.make(container, "BYE BYE", Snackbar.LENGTH_SHORT).show();
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void setupInjection() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        component = app.getRecipeMainComponent(this, this);
        imageLoader = getImageLoader();
        presenter = getPresenter();
    }

    public void setupImageLoader(){
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                Log.e("activity", "imagen de recipe error");
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.e("activity", "imagen de recipe lista");
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinisImageLoadingListener(glideRequestListener);
    }

    @Override
    public void handleProgressBar(boolean mostrar) {
        int visible = View.VISIBLE;
        if(!mostrar){
            visible = View.GONE;
        }
        progressBar.setVisibility(visible);
    }

    @Override
    public void handleUiElements(boolean mostrar) {
        int visible = View.VISIBLE;
        if(!mostrar){
            visible = View.GONE;
        }
        imgDismiss.setVisibility(visible);
        imgKeep.setVisibility(visible);
        imgRecipe.setVisibility(visible);
    }

    @OnClick(R.id.imgKeep)
    @Override
    public void onKeep(){
        if (currentRecipe != null){
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.imgDismiss)
    @Override
    public void onDismiss(){
        presenter.dismissRecipe();
    }

    @Override
    public void saveAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.save_animation);
        animation.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(animation);
    }

    @Override
    public void dismissAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dismiss_animation);
        animation.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(animation);
    }

    private Animation.AnimationListener getAnimationListener(){
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    private void clearImage(){
        imgRecipe.setImageResource(0);
    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(
                container,
                getString(R.string.main_notice_saved),
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        Log.e("Activity", "Setteando -" + recipe.getImageUrl() + "-");
        imageLoader.load(imgRecipe, recipe.getImageUrl());
        presenter.imageReady();
        this.currentRecipe = recipe;
    }

    @Override
    public void getRecipeError(String error) {
        Snackbar.make(
                container,
                String.format(getString(R.string.main_error), error),
                Snackbar.LENGTH_LONG
        ).show();
    }

    public ImageLoader getImageLoader() {
        return component.providesImageLoader();
    }

    public RecipeMainPresenter getPresenter() {
        return component.getRecipeMainPresenter();
    }
}
