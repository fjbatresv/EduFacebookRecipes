package edu.galileo.android.facebookrecipes.libs.base;

/**
 * Created by javie on 14/06/2016.
 */
public interface EventBus {
    void register(Object suscriber);
    void unRegister(Object suscriber);
    void post(Object event);
}
