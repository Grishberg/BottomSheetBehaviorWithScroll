package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grishberg.coordinatorlayoutmenu.R;

public class MenuItemView {
    private final TextView titleView;
    private final ImageView iconView;
    private final View itemRootView;

    public MenuItemView(View itemRootView) {
        this.itemRootView = itemRootView;
        titleView = itemRootView.findViewById(R.id.menuTitle);
        iconView = itemRootView.findViewById(R.id.menuIcon);
    }

    public void populateWithItem(MenuItem item) {
        item.drawIcon(iconView);
        item.writeText(titleView);
    }

    public void addsToParent(ViewGroup parent) {
        parent.addView(itemRootView);
    }
}
