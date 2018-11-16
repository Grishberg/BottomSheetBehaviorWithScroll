package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grishberg.coordinatorlayoutmenu.R;

public class MenuItemsFactory {
    private final Context context;
    private final ViewGroup rootMenu;

    public MenuItemsFactory(Context context, ViewGroup rootMenu) {
        this.context = context;
        this.rootMenu = rootMenu;
    }

    public MenuItemView createMenuItemView(MenuItem item) {
        View itemRootView = LayoutInflater.from(context)
                .inflate(R.layout.menu_item_layout, rootMenu, false);
        MenuItemView menuItemView = new MenuItemView(itemRootView);
        menuItemView.populateWithItem(item);
        return menuItemView;
    }
}
