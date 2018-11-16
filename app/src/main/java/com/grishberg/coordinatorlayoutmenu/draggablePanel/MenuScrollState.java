package com.grishberg.coordinatorlayoutmenu.draggablePanel;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;

import java.util.ArrayList;

public class MenuScrollState {
    private ArrayList<ScrollStateChangedListener> listeners = new ArrayList<>();
    private BottomSheetBehavior<View> anchorBehavior;

    public MenuScrollState(BottomSheetBehavior<View> anchorBehavior) {
        this.anchorBehavior = anchorBehavior;
    }

    public void subscibeListener(ScrollStateChangedListener listener) {
        if (listeners.isEmpty()) {
            anchorBehavior.setBottomSheetCallback(new BottomSheetCallbackListener());
        }
        listeners.add(listener);
    }

    public void unsubscribeListener(ScrollStateChangedListener listener) {
        listeners.remove(listener);
    }

    private class BottomSheetCallbackListener extends BottomSheetBehavior.BottomSheetCallback {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_COLLAPSED:
                    notifyCollapsed();
                    break;
                case BottomSheetBehavior.STATE_EXPANDED:
                    notifyExpanded();
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            notifyOnSlide(slideOffset);
        }
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
