package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.util.Log;
import android.view.View;

import com.grishberg.coordinatorlayoutmenu.widgets.BottomSheetBehavior;
import com.grishberg.coordinatorlayoutmenu.widgets.CustomRecyclerView;

public class MenuScroll {
    private static final String TAG = MenuScroll.class.getSimpleName();
    private BottomSheetBehavior<View> behavior;

    public MenuScroll(CustomRecyclerView rv, BottomSheetBehavior<View> behavior) {
        this.behavior = behavior;
        rv.setTouchAndScrollListener(new ScrollListener());
    }

    private class ScrollListener implements CustomRecyclerView.TouchAndScrollEventsListener {
        @Override
        public void onStartDrag() {
            Log.d(TAG, "onStartDrag: ");
            behavior.setScrollDownWhenNestedScroll(false);
        }

        @Override
        public void onActionUp() {
            Log.d(TAG, "onActionUp: ");
            behavior.setScrollDownWhenNestedScroll(true);
        }
    }
}
