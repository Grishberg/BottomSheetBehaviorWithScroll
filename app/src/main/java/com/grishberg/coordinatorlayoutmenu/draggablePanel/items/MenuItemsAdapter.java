package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grishberg.coordinatorlayoutmenu.R;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {
    private final Context context;
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public MenuItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.menu_item_layout, viewGroup, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder menuItemViewHolder, int i) {
        menuItemViewHolder.bindWithItem(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void populateWithItems(List<MenuItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }
}
