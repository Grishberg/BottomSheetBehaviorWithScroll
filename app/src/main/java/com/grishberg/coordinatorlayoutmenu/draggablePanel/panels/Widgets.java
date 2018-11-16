package com.grishberg.coordinatorlayoutmenu.draggablePanel.panels;

import android.view.View;

import com.grishberg.coordinatorlayoutmenu.draggablePanel.MenuScrollState;

public class Widgets implements MenuScrollState.ScrollStateChangedListener {
    private final View widgetsRoot;

    public Widgets(View widgetsRoot) {
        this.widgetsRoot = widgetsRoot;
    }

    @Override
    public void onSlide(float progress) {
        widgetsRoot.setAlpha(progress);
    }

    @Override
    public void onCollapsed() {
        widgetsRoot.setAlpha(0.0f);
    }

    @Override
    public void onExpanded() {
        widgetsRoot.setAlpha(1.0f);
    }
}
