package com.wn.quickrechatge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by ds on 2017/1/16.
 */
public class DSDialog extends Dialog{
    private BaseViewHolder viewHolder;
    private WindowManager.LayoutParams params;
    private Window mWindow;

    private DSDialog(Context context) {
        super(context);
    }

    private DSDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    public static class Builder {
        private int width = WindowManager.LayoutParams.WRAP_CONTENT;//dialog 宽度
        private int height = WindowManager.LayoutParams.WRAP_CONTENT;//dialog 高度
        private float alpha = 1f;//showing View alpha
        private float backAlpha = .5f;//背景灰度
        private int gravity = Gravity.CENTER;//dialog显示位置
        private int layoutId;
        private View contentView;
        private int styleId = android.support.design.R.style.Animation_AppCompat_Dialog;
        private int themeResId;
        private Context mCtx;
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside = true;
        private OnCancelListener onCancelListener;
        private OnDismissListener onDismissListener;
        private OnShowListener onShowListener;
        private int x;
        private int y;

        public Builder(Context ctx) {
            this.mCtx = ctx;
        }

        public Builder setThemeResId(int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        public Builder setContentView(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return setContentView(LayoutInflater.from(mCtx).inflate(layoutId, null));
        }

        public Builder setContentView(@NonNull View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder setWight(int w) {
            this.width = w;
            return this;
        }

        public Builder setHeight(int h) {
            this.height = h;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setWindowAnimations(@StyleRes int styleId) {
            this.styleId = styleId;
            return this;
        }

        public Builder setViewAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setBackAlpha(float backAlpha) {
            this.backAlpha = backAlpha;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener cancelListener) {
            this.onCancelListener = cancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnShowListener(OnShowListener onShowListener) {
            this.onShowListener = onShowListener;
            return this;
        }

        public DSDialog create() {
            final DSDialog dialog = new DSDialog(mCtx, themeResId);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(contentView);
            dialog.viewHolder = new BaseViewHolder(contentView);
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

            params.width = this.width;
            params.height = this.height;
            params.alpha = this.alpha;
            params.gravity = this.gravity;
//            params.x = this.x;
//            params.y = this.y;

            dialog.getWindow().setAttributes(params);
            dialog.getWindow().setDimAmount(this.backAlpha);
            dialog.setCancelable(cancelable);
            if (cancelable) {
                dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            }
            dialog.getWindow().setWindowAnimations(styleId);

            dialog.setOnCancelListener(onCancelListener);
            dialog.setOnDismissListener(onDismissListener);
            dialog.setOnShowListener(onShowListener);

            return dialog;
        }
    }

    public BaseViewHolder getViewHolder() {
        return viewHolder;
    }
}
