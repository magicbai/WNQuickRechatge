package com.wn.quickrechatge.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wn.quickrechatge.R;

import org.xutils.common.util.DensityUtil;


/**
 * Created by ds on 2016/7/12.
 * recyclerView分割线
 */
public class MyDivier extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    private int divierColor;
    private int divierHeight;
    private Paint mPaint;
    private int headerSize=0;

    private int spanCount;

    public MyDivier(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
//        mDivider = a.getDrawable(0);
        divierColor = R.color.default_color;
        divierHeight = 20;
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }

    public void setDivierColor(int divierColor) {
        this.divierColor = divierColor;
        initPaint();
    }

    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    public void setDivierHeight(int divierHeight) {
        this.divierHeight = DensityUtil.dip2px(divierHeight);
    }
    public void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(divierColor);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + divierHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
            if (mPaint != null) {
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + divierHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
            if (mPaint != null) {
                    c.drawRect(left, top, right, bottom, mPaint);
            }


        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (parent.getLayoutManager() instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        }
        if (mOrientation == VERTICAL_LIST) {
            if (headerSize>0){
                if (itemPosition>headerSize-1){
                    outRect.set(0, 0, 0, divierHeight);
                }
            }
            else{
                outRect.set(0, 0, 0, divierHeight);
            }

        } else {
            if (isRect(itemPosition)){
                outRect.set(0, 0, divierHeight, 0);
            }
        }
    }
    public boolean isRect(int  itemPosition){
        itemPosition++;
        if (itemPosition == 1){
            return true;
        }
        if (headerSize%spanCount == 0 && itemPosition%spanCount !=0){
            return true;
        }
        if (headerSize%spanCount != 0 && itemPosition%spanCount !=0){
            return true;
        }
        return false;
    }



}
