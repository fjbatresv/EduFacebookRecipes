package edu.galileo.android.facebookrecipes.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;

/**
 * Created by javie on 14/06/2016.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private RequestListener onFinishLoadingListener;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imgAvatar, String s) {
        if(onFinishLoadingListener != null){
            glideRequestManager
                    .load(s)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishLoadingListener)
                    .into(imgAvatar);
        }else{
            glideRequestManager
                    .load(s)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imgAvatar);
        }
    }

    @Override
    public void setOnFinisImageLoadingListener(Object listener) {
        if(listener instanceof RequestListener){
            this.onFinishLoadingListener = (RequestListener) listener;
        }
    }
}
