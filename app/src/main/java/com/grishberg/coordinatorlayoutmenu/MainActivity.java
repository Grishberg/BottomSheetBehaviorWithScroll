package com.grishberg.coordinatorlayoutmenu;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.grishberg.coordinatorlayoutmenu.draggablePanel.Menu;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.MenuScrollState;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItem;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItems;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItemsFactory;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Omnibar;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Pip;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Widgets;
import com.grishberg.coordinatorlayoutmenu.widgets.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> anchorBehavior;
    private State state = new InitialState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NestedScrollView scrollView = findViewById(R.id.bottomSheetPanel);
        anchorBehavior = BottomSheetBehavior.from((View) scrollView);

        anchorBehavior.setHideable(false);
        anchorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        findViewById(R.id.topItemSpace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTap(v);
            }
        });

        MenuScrollState menuScrollState = new MenuScrollState(anchorBehavior);

        ViewGroup menuRoot = findViewById(R.id.itemsLayout);
        MenuItemsFactory menuItemsFactory = new MenuItemsFactory(this, menuRoot);
        MenuItems menuItems = new MenuItems(menuItemsFactory, menuRoot);
        menuItems.populateWithItems(createItems());

        View widgetsView = findViewById(R.id.widgetsPanel);
        View omnibarView = findViewById(R.id.omnibar);
        View pipView = findViewById(R.id.pip);
        Menu menu = new Menu(menuItems,
                new Widgets(widgetsView),
                new Omnibar(omnibarView),
                new Pip(pipView),
                menuScrollState);
        state = new CreatedState(menu, scrollView);

        WebView webView = findViewById(R.id.webContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ya.ru");
    }

    private List<MenuItem> createItems() {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            String title = String.format(Locale.US, "item %d", i + 1);
            Drawable drawable = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_map);
            result.add(new MenuItem(title, drawable));
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        state.onBackPressed();
    }

    public void onTap(View view) {
        state.onViewPortClicked();
        anchorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private class InitialState extends State {
        /* stub */
    }

    private class CreatedState extends State implements Menu.NotConsumedBackPressDelegate {
        private final Menu menu;
        private NestedScrollView scrollView;

        CreatedState(Menu menu, NestedScrollView scrollView) {
            this.menu = menu;
            this.scrollView = scrollView;
        }

        @Override
        void onBackPressed() {
            menu.onBackPressed(this);
        }

        @Override
        public void onBackPressNotConsumed() {
            super.onBackPressed();
        }

        @Override
        void onViewPortClicked() {
            scrollView.scrollTo(0, 0);
        }
    }

    private abstract static class State {
        void onBackPressed() { /* template */ }

        void onViewPortClicked() { /* template */ }
    }
}
