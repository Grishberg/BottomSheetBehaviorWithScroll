package com.grishberg.coordinatorlayoutmenu.draggablePanel;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ExpandableSnapHelper extends LinearSnapHelper {
    private OrientationHelper mVerticalHelper, mHorizontalHelper;

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];

        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {

        if (layoutManager instanceof LinearLayoutManager) {

            if (layoutManager.canScrollHorizontally()) {
                return getStartView(layoutManager, getHorizontalHelper(layoutManager));
            } else {
                return getStartView(layoutManager, getVerticalHelper(layoutManager));
            }
        }

        return super.findSnapView(layoutManager);
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    private View getStartView(RecyclerView.LayoutManager layoutManager,
                              OrientationHelper helper) {
        LinearLayoutManager lm = (LinearLayoutManager) layoutManager;
        int firstChild = lm.findFirstVisibleItemPosition();

        boolean isLastItem = lm.findLastCompletelyVisibleItemPosition()
                == layoutManager.getItemCount() - 1;

        if (firstChild == RecyclerView.NO_POSITION || firstChild > 0 || isLastItem) {
            return null;
        }

        View child = layoutManager.findViewByPosition(firstChild);

        if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                && helper.getDecoratedEnd(child) > 0) {
            return child;
        } else {
            if (lm.findLastCompletelyVisibleItemPosition()
                    == layoutManager.getItemCount() - 1) {
                return null;
            } else if (firstChild == 0){
                return layoutManager.findViewByPosition(firstChild + 1);
            }
        }
        return super.findSnapView(layoutManager);
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
