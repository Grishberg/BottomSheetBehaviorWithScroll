package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;;

import java.util.List;

public class MenuItems {
    private final MenuItemsAdapter itemsAdapter;

    public MenuItems(MenuItemsAdapter itemsAdapter) {
        this.itemsAdapter = itemsAdapter;
    }

    public void populateWithItems(List<MenuItem> items) {
        itemsAdapter.populateWithItems(items);
    }
}
