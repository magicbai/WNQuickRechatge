package com.wn.quickrechatge.views;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ds on 2016/11/15.
 */

public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;
    private int mSpanCount;
    private int mRadixX;
    private int mCountInFirstLine = 1;

    public GridLayoutItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager){
            mSpanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        }
        this.mRadixX = mSpace / mSpanCount;
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int position = params.getViewPosition();
        final int spanSize;
        final int index;

        if (params instanceof GridLayoutManager.LayoutParams) {
            GridLayoutManager.LayoutParams gridParams = (GridLayoutManager.LayoutParams) params;
            spanSize = gridParams.getSpanSize();
            index = gridParams.getSpanIndex();
            //calculate real num in line
            if (index == 0 && mSpanCount > 1 && position == 0) {
                int tempPosition = position;
                int countInLine = 0;
                int spanIndex;
                do {
                    countInLine++;
                    if (tempPosition < state.getItemCount() - 1) {
                        spanIndex = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup().getSpanIndex(++tempPosition, mSpanCount);
                    } else {
                        spanIndex = 0;
                    }
                } while (spanIndex != 0);

                if (position == 0)
                    mCountInFirstLine = countInLine;
            }
        } else {
            spanSize = 1;
            index = 0;
        }

        // invalid value
        if (spanSize < 1 || index < 0 || spanSize > mSpanCount)
            return;

        outRect.left = mSpace - mRadixX * index;
        outRect.right = mRadixX + mRadixX * (index + spanSize - 1);

        // set top to all in first lane
        if (position < mCountInFirstLine) {
            outRect.top = mSpace;
        }
        outRect.bottom = mSpace;
    }

}
