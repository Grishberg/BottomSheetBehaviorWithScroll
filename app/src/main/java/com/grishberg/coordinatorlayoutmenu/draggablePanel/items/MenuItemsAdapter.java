package com.grishberg.coordinatorlayoutmenu.draggablePanel.items;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grishberg.coordinatorlayoutmenu.R;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsAdapter extends RecyclerView.Adapter<AbsViewHolder> {
    private static final int SPACE_POS = 0;
    private static final int ITEM_POS = 1;

    private static final int SPACE_TYPE = 0;
    private static final int ITEM_TYPE = 1;

    private final Context context;
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public MenuItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AbsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (getItemViewType(i)) {
            case SPACE_TYPE:
                return createSpaceViewHolder(viewGroup);
            default:
                return createItemViewHolder(viewGroup);
        }
    }

    private AbsViewHolder createSpaceViewHolder(ViewGroup viewGroup) {
        View view = inflateView(viewGroup, R.layout.top_space_item_layout);
        return new SpaceViewHolder(view);
    }

    private AbsViewHolder createItemViewHolder(ViewGroup viewGroup) {
        View view = inflateView(viewGroup, R.layout.menu_item_layout);
        return new MenuItemViewHolder(view);
    }

    private View inflateView(@NonNull ViewGroup viewGroup, @LayoutRes int res) {
        return LayoutInflater.from(context).inflate(res, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsViewHolder menuItemViewHolder, int i) {
        if (getItemViewType(i) == ITEM_TYPE) {
            menuItemViewHolder.bindWithItem(items.get(i - 1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case SPACE_POS:
                return SPACE_TYPE;
            default:
                return ITEM_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return items.size() + 1;
    }

    void populateWithItems(List<MenuItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }
}
