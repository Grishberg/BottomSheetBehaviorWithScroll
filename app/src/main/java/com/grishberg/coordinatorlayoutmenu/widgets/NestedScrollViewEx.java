package com.grishberg.coordinatorlayoutmenu.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.grishberg.coordinatorlayoutmenu.R;

public class NestedScrollViewEx extends NestedScrollView {
    private MenuContainer menuContainer;
    private final int menuHeight;
    private int menuTopMargin;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private View omnibarView;

    public NestedScrollViewEx(@NonNull Context context) {
        this(context, null);
    }

    public NestedScrollViewEx(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        menuHeight = context.getResources().getDimensionPixelSize(R.dimen.menuHeight);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        menuContainer = findViewById(R.id.menuPanel);
        bottomSheetBehavior = BottomSheetBehavior.from((View) this);
        omnibarView = findViewById(R.id.omnibar);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        menuTopMargin = calculateMenuTopMargin(getMeasuredHeight());
        bottomSheetBehavior.setPeekHeight(menuTopMargin + omnibarView.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        menuContainer.setScreenHeight(h);
        MarginLayoutParams lp = (MarginLayoutParams) menuContainer.getLayoutParams();
        lp.topMargin = menuTopMargin;
        bottomSheetBehavior.setmUntouchableAreaOffset(menuTopMargin);
    }

    private int calculateMenuTopMargin(int height) {
        return height - menuHeight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (shouldIgnoreTouchEvent(menuContainer, ev)) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    private boolean shouldIgnoreTouchEvent(View child, MotionEvent event) {
        return event.getY() < child.getTop() - getScrollY();
    }
}
