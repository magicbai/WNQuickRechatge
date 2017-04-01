package com.wn.quickrechatge.dialog;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.wn.quickrechatge.R;
import com.wn.quickrechatge.base.BaseDialog;
import com.wn.quickrechatge.views.MyDatePicker;


/**
 * Created by ds on 2017/1/16..
 *
 */
public class DatePickerDialog extends BaseDialog {

    private OnDateSelectedListener onDateSelectedListener;
    private MyDatePicker datePicker;

    public DatePickerDialog(Activity mAty) {
        super(mAty);
        datePicker = dialog.getViewHolder().getView(R.id.dialog_datepicker);
//        datePicker.setDividerColor(R.color.color33);
    }

    @Override
    protected int getContentViewID() {
        return R.layout.dialog_datepicker;
    }

    /**
     * 隐藏日期
     */
    public void hideDay() {
        hideView(2);
    }

    /**
     * 隐藏年份
     */
    public void hideMonth() {
        hideView(1);
    }

    /**
     * 隐藏年份
     */
    public void hideYeay() {
        hideView(0);
    }

    private void hideView(int index) {
        ((ViewGroup) ((ViewGroup) datePicker.getChildAt(0)).getChildAt(0)).getChildAt(index).setVisibility(View.GONE);
    }

    @Override
    public BaseDialog setRightBtnOnClickListener(View.OnClickListener l) {
        dialog.getViewHolder().setOnClickListener(R.id.tvLeft, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        return this;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setOnDateSelectedListener(final OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
        dialog.getViewHolder().setOnClickListener(R.id.tvRight, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDateSelectedListener != null) {
                    onDateSelectedListener.onDateSelectedListener(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                }
            }
        });
    }

    public interface OnDateSelectedListener {
        void onDateSelectedListener(int year, int month, int day);
    }
}
