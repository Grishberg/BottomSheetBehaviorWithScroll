package com.grishberg.coordinatorlayoutmenu.draggablePanel;

import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItems;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Omnibar;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Pip;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Widgets;

public class Menu {
    private final MenuItems menuItems;
    private final Widgets widgets;
    private final Omnibar omnibar;
    private final Pip pip;
    private MenuScrollState menuScrollState;

    public Menu(MenuItems menuItems,
                Widgets widgets,
                Omnibar omnibar,
                Pip pip,
                MenuScrollState menuScrollState) {
        this.menuItems = menuItems;
        this.widgets = widgets;
        this.omnibar = omnibar;
        this.pip = pip;
        this.menuScrollState = menuScrollState;

        menuScrollState.subscibeListener(widgets);
        menuScrollState.subscibeListener(omnibar);
        menuScrollState.subscibeListener(pip);
    }

    public void onBackPressed(NotConsumedBackPressDelegate delegate) {
        boolean notConsumed = false;
        if (notConsumed) {
            delegate.onBackPressNotConsumed();
        }
    }

    public interface NotConsumedBackPressDelegate {
        void onBackPressNotConsumed();
    }
}
