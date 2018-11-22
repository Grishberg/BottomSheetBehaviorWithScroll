package com.grishberg.coordinatorlayoutmenu.draggablePanel;

import java.util.ArrayList;

public class MenuScrollState {
    private ArrayList<ScrollStateChangedListener> listeners = new ArrayList<>();
    private ExpandableSnapHelper snapHelper;

    public MenuScrollState(ExpandableSnapHelper snapHelper) {
        this.snapHelper = snapHelper;
    }

    public void subscibeListener(ScrollStateChangedListener listener) {
        if (listeners.isEmpty()) {
            //anchorBehavior.setBottomSheetCallback(new BottomSheetCallbackListener());
        }
        listeners.add(listener);
    }

    public void unsubscribeListener(ScrollStateChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyOnSlide(float slideOffset) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onSlide(slideOffset);
        }
    }

    private void notifyCollapsed() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onCollapsed();
        }
    }

    private void notifyExpanded() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onExpanded();
        }
    }

    public interface ScrollStateChangedListener {
        void onSlide(float progress);

        void onCollapsed();

        void onExpanded();
    }
}
