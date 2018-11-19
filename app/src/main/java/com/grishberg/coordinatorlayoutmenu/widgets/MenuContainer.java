package com.grishberg.coordinatorlayoutmenu.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.grishberg.coordinatorlayoutmenu.R;

public class MenuContainer extends FrameLayout {
    private int screenHeight;
    private final int menuHeight;

    public MenuContainer(Context context) {
        this(context, null);
    }

    public MenuContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        menuHeight = context.getResources().getDimensionPixelSize(R.dimen.menuHeight);
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
        lp.topMargin = screenHeight - menuHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
