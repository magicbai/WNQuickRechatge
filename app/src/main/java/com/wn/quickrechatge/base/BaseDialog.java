package com.wn.quickrechatge.base;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.wn.quickrechatge.R;
import com.wn.quickrechatge.dialog.DSDialog;


import org.xutils.common.util.DensityUtil;

/**
 * Created by ds on 2017/1/16.
 */
public abstract class BaseDialog {
    protected DSDialog dialog;
    protected BaseViewHolder viewHolder;

    public BaseDialog(Activity mAty) {
        dialog = new DSDialog.Builder(mAty)
                .setContentView(getContentViewID())
                .setWight((int) (DensityUtil.getScreenWidth() * 0.9f))
                .create();
        viewHolder = dialog.getViewHolder();
    }

    /**
     * 返回布局ID
     *
     * @return @{@link android.support.annotation.LayoutRes}
     */
    protected abstract int getContentViewID();

    public void show() {
        dialog.show();
    }

    public void close() {
        dialog.dismiss();
    }

    public BaseDialog setTitle(CharSequence title) {
        viewHolder.setText(R.id.tvTitle, title);
        return this;
    }

    public BaseDialog setLeftBtnOnClickListener(View.OnClickListener l) {
        viewHolder.setOnClickListener(R.id.tvLeft, l);
        return this;
    }

    public BaseDialog setRightBtnOnClickListener(View.OnClickListener l) {
        viewHolder.setOnClickListener(R.id.tvRight, l);
        return this;
    }

    public BaseDialog setOnViewClickListener(@IdRes int viewId, View.OnClickListener l) {
        viewHolder.setOnClickListener(viewId, l);
        return this;
    }
}
