package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.util.Log;

import com.grishberg.coordinatorlayoutmenu.widgets.CustomRecyclerView;

public class MenuScroll {
    private static final String TAG = MenuScroll.class.getSimpleName();

    public MenuScroll(CustomRecyclerView rv) {
        rv.setTouchAndScrollListener(new ScrollListener());
    }

    private class ScrollListener implements CustomRecyclerView.TouchAndScrollEventsListener {
        @Override
        public void onStartDrag() {
            Log.d(TAG, "onStartDrag: ");
        }

        @Override
        public void onActionUp() {
            Log.d(TAG, "onActionUp: ");
        }
    }
}
