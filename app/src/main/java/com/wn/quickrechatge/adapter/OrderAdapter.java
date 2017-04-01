package com.wn.quickrechatge.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wn.quickrechatge.bean.OrderBean;

import java.util.List;

/**
 * Created by ds on 2017/3/29.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderBean,BaseViewHolder> {
    public OrderAdapter(int layoutResId, List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

    }
}
