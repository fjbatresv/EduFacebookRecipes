package edu.galileo.android.facebookrecipes.login.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.list.ui.RecipeListActivity;
import edu.galileo.android.facebookrecipes.main.ui.RecipeMainActivity;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.btnLlogin)
    LoginButton btnLogin;
    @Bind(R.id.container)
    RelativeLayout container;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(AccessToken.getCurrentAccessToken() != null){
            navigateToMainScreen();
        }
        callbackManager = CallbackManager.Factory.create();
        btnLogin.setPublishPermissions(Arrays.asList("publish_actions"));
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                navigateToMainScreen();
            }

            @Override
            public void onCancel() {
                Snackbar.make(
                        container,
                        getString(R.string.login_cancel_error),
                        Snackbar.LENGTH_LONG
                ).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(
                        container,
                        String.format(
                                getString(
                                        R.string.login_error
                                ),
                                error.getLocalizedMessage()
                        ),
                        Snackbar.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void navigateToMainScreen(){
        startActivity(new Intent(this, RecipeMainActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
        ));
    }
}
