package com.grishberg.coordinatorlayoutmenu;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;

import com.grishberg.coordinatorlayoutmenu.draggablePanel.Menu;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.MenuScrollState;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItem;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItems;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuItemsAdapter;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.items.MenuScroll;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Omnibar;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Pip;
import com.grishberg.coordinatorlayoutmenu.draggablePanel.panels.Widgets;
import com.grishberg.coordinatorlayoutmenu.widgets.BottomSheetBehavior;
import com.grishberg.coordinatorlayoutmenu.widgets.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> behavior;
    private State state = new InitialState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        behavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetPanel));

        behavior.setHideable(false);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        MenuScrollState menuScrollState = new MenuScrollState(behavior);

        CustomRecyclerView rv = findViewById(R.id.itemsRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rv.setLayoutManager(layoutManager);
        MenuItemsAdapter adapter = new MenuItemsAdapter(this);
        rv.setAdapter(adapter);

        MenuItems menuItems = new MenuItems(adapter);
        menuItems.populateWithItems(createItems());

        View widgetsView = findViewById(R.id.widgetsPanel);
        View omnibarView = findViewById(R.id.omnibar);
        View pipView = findViewById(R.id.pip);

        MenuScroll menuScroll = new MenuScroll(rv, behavior);
        Menu menu = new Menu(menuItems,
                menuScroll,
                new Widgets(widgetsView),
                new Omnibar(omnibarView),
                new Pip(pipView),
                menuScrollState);
        state = new CreatedState(menu);
        rv.subscribeScrollEvents();

        WebView webView = findViewById(R.id.webContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ya.ru");
    }

    private List<MenuItem> createItems() {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
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
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private class InitialState extends State {
        /* stub */
    }

    private class CreatedState extends State implements Menu.NotConsumedBackPressDelegate {
        private final Menu menu;

        CreatedState(Menu menu) {
            this.menu = menu;
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
        }
    }

    private abstract static class State {
        void onBackPressed() { /* template */ }

        void onViewPortClicked() { /* template */ }
    }
}
