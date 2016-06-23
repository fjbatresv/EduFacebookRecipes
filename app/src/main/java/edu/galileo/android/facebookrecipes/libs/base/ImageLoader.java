package edu.galileo.android.facebookrecipes.libs.base;

import android.widget.ImageView;

/**
 * Created by javie on 14/06/2016.
 */
public interface ImageLoader {
    void load(ImageView imgAvatar, String s);
    void setOnFinisImageLoadingListener(Object listener);
}
