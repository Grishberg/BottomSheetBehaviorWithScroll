package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grishberg.coordinatorlayoutmenu.R;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;
    private final ImageView iconView;

    public MenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.menuTitle);
        iconView = itemView.findViewById(R.id.menuIcon);
    }

    void bindWithItem(MenuItem item) {
        item.drawIcon(iconView);
        item.writeText(titleView);
    }
}
