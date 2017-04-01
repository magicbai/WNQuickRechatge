package com.wn.quickrechatge.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wn.quickrechatge.R;
import com.wn.quickrechatge.adapter.OrderAdapter;
import com.wn.quickrechatge.base.BaseFragment;
import com.wn.quickrechatge.bean.OrderBean;
import com.wn.quickrechatge.views.GridLayoutItemDecoration;
import com.wn.quickrechatge.views.MyDivier;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 */
public class OrderFragment extends BaseFragment {
    private View mainView;
    private RecyclerView recyclerView;
    private Context ctx;

    private OrderAdapter adapter;
    private List<OrderBean> orderList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_order, container, false);
        ctx = getActivity();
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
        dataChanged();
    }

    private void initViews() {
        recyclerView = (RecyclerView) mainView.findViewById(R.id.order_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        MyDivier de = new MyDivier(ctx,MyDivier.VERTICAL_LIST);
        recyclerView.addItemDecoration(de);
    }
    private void initData(){
        orderList.add(new OrderBean());
        orderList.add(new OrderBean());
        orderList.add(new OrderBean());
        orderList.add(new OrderBean());
        orderList.add(new OrderBean());
        orderList.add(new OrderBean());
    }
    private void dataChanged(){
        if (adapter == null){
            adapter = new OrderAdapter(R.layout.adapter_order,orderList);
            recyclerView.setAdapter(adapter);
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }
}
