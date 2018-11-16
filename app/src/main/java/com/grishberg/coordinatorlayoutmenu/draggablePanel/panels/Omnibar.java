package com.grishberg.coordinatorlayoutmenu.draggablePanel.panels;

import android.view.View;

import com.grishberg.coordinatorlayoutmenu.draggablePanel.MenuScrollState;

public class Omnibar implements MenuScrollState.ScrollStateChangedListener {
    private final View omnibarView;

    public Omnibar(View omnibarView) {
        this.omnibarView = omnibarView;
    }

    @Override
    public void onSlide(float progress) {
        omnibarView.setAlpha(1 - progress);
    }

    @Override
    public void onCollapsed() {
        omnibarView.setAlpha(1.0f);
    }

    @Override
    public void onExpanded() {
        omnibarView.setAlpha(0.0f);
    }
}
