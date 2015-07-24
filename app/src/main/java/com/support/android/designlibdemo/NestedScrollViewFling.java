package com.support.android.designlibdemo;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Cagkan on 24.7.2015.
 */
public class NestedScrollViewFling extends NestedScrollView
{
    private OnFlingEndReachedTopListener mListener;
    private Boolean isBeingTouched = false;

    public NestedScrollViewFling(Context context)
    {
        super(context);
    }

    public NestedScrollViewFling(Context context, AttributeSet attrs,
                                 int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public NestedScrollViewFling(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        int action = ev.getAction();

        if ((action == MotionEvent.ACTION_DOWN) || (action == MotionEvent.ACTION_MOVE))
            isBeingTouched = true;
        else
            isBeingTouched = false;

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY)
    {
        if (clampedY && scrollY == 0)
        {
            if (mListener != null)
                mListener.onTopReached(isBeingTouched);
        }

        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public OnFlingEndReachedTopListener getOnTopReachedListener()
    {
        return mListener;
    }

    public void setOnTopReachedListener(
            OnFlingEndReachedTopListener onTopReachedListener)
    {
        mListener = onTopReachedListener;
    }


    /**
     * Event listener.
     */
    public interface OnFlingEndReachedTopListener
    {
        public void onTopReached(Boolean isBeingTouch);
    }
}
