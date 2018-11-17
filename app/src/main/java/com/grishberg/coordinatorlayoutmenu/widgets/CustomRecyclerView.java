package com.grishberg.coordinatorlayoutmenu.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CustomRecyclerView extends RecyclerView {
    private TouchAndScrollEventsListener touchAndScrollListener
            = TouchAndScrollEventsListener.STUB;
    private boolean isDragging;

    public CustomRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void subscribeScrollEvents() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                int offset = computeVerticalScrollOffset();
                Log.d("MenuScroll", "onScrollStateChanged: " + newState + ", offset = " + offset);
                if (newState == 1 && !isDragging && offset > 0) {
                    touchAndScrollListener.onStartDrag();
                    isDragging = true;
                }
            }
        });
    }

    public void setTouchAndScrollListener(TouchAndScrollEventsListener touchAndScrollListener) {
        this.touchAndScrollListener = touchAndScrollListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (isDragging && e.getAction() == MotionEvent.ACTION_UP) {
            isDragging = false;
            touchAndScrollListener.onActionUp();
        }
        return super.onTouchEvent(e);
    }

    public interface TouchAndScrollEventsListener {
        void onStartDrag();

        void onActionUp();

        TouchAndScrollEventsListener STUB = new TouchAndScrollEventsListener() {
            @Override
            public void onStartDrag() { /* stub */ }

            @Override
            public void onActionUp() { /* stub */ }
        };
    }
}
