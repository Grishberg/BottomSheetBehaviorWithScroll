package com.grishberg.coordinatorlayoutmenu.draggablePanel.panels;

import android.view.View;

import com.grishberg.coordinatorlayoutmenu.draggablePanel.MenuScrollState;

public class Pip implements MenuScrollState.ScrollStateChangedListener {
    private final View pipView;

    public Pip(View pipView) {
        this.pipView = pipView;
    }

    @Override
    public void onSlide(float progress) {
        pipView.setAlpha(progress);
    }

    @Override
    public void onCollapsed() {
        pipView.setAlpha(0f);
    }

    @Override
    public void onExpanded() {
        pipView.setAlpha(1f);
    }
}
