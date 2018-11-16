package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItem {
    private final String title;
    private final Drawable icon;

    public MenuItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    public void writeText(TextView titleView) {
        titleView.setText(title);
    }

    public void drawIcon(ImageView iconView) {
        iconView.setImageDrawable(icon);
    }
}
