package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.view.ViewGroup;

import java.util.List;

public class MenuItems {
    private final MenuItemsFactory menuItemsFactory;
    private final ViewGroup parent;

    public MenuItems(MenuItemsFactory menuItemsFactory, ViewGroup parent) {
        this.menuItemsFactory = menuItemsFactory;
        this.parent = parent;
    }

    public void populateWithItems(List<MenuItem> items) {
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            MenuItemView itemView = menuItemsFactory.createMenuItemView(item);
            itemView.addsToParent(parent);
        }
    }
}
