package edu.galileo.android.facebookrecipes.main.ui;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by javie on 23/06/2016.
 */
public class SwypeGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final int swypeThreshold = 100;
    private static final int swypeVelocityThreshold = 100;

    private SwipeGestureListener listener;

    public SwypeGestureDetector(SwipeGestureListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffY = e2.getY() - e1.getY();
        float diffX = e2.getX() - e1.getX();
        if (Math.abs(diffX) > Math.abs(diffY)){
            if (Math.abs(diffX) > swypeThreshold && Math.abs(velocityX) > swypeVelocityThreshold){
                if (diffX > 0){
                    listener.onKeep();
                }else {
                    listener.onDismiss();
                }
            }
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
