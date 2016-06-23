package edu.galileo.android.facebookrecipes.libs.DI;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.facebookrecipes.libs.GlideImageLoader;
import edu.galileo.android.facebookrecipes.libs.GreenRobotEventBus;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;


/**
 * Created by javie on 15/06/2016.
 */
@Module
public class LibsModule {

    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Singleton
    @Provides
    public EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Singleton
    @Provides
    public org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Singleton
    @Provides
    public ImageLoader providesImageLoader(RequestManager manager){
        return new GlideImageLoader(manager);
    }

    @Singleton
    @Provides
    public RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Singleton
    @Provides
    public Activity providesActivity(){
        return this.activity;
    }
}
