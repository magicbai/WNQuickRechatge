package com.wn.quickrechatge.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wn.quickrechatge.R;
import com.wn.quickrechatge.activity.login.LoginActivity;
import com.wn.quickrechatge.base.BaseFragment;

/**
 * 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private View mainView;
    private ImageView ivHead;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                mainView = inflater.inflate(R.layout.fragment_mine, container, false);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        ivHead = (ImageView) mainView.findViewById(R.id.mine_ivHead);
        ivHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mine_ivHead:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
