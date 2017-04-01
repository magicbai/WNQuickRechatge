package com.wn.quickrechatge.activity;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wn.quickrechatge.R;
import com.wn.quickrechatge.base.BaseActivity;
import com.wn.quickrechatge.fragment.MineFragment;
import com.wn.quickrechatge.fragment.OrderFragment;
import com.wn.quickrechatge.fragment.RechargeFragment;
import com.wn.quickrechatge.views.MyViewPager;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private MyViewPager viewPager;
    private RadioGroup rg;
    private RadioButton rbRecharge;

    private List<Fragment> listFrg = new ArrayList<>();
    private RechargeFragment rechargeFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //权限申请
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        requestPermission(permissions,0);
        initViews();
        initData();
        initListener();
    }


    @Override
    public void initViews() {
        super.initViews();
        setTitle(getString(R.string.recharge));
        setLeftTitle("筛选");
        setRightTitle("列表");
        hideBack();

        viewPager = (MyViewPager) findViewById(R.id.main_viewPager);
        rg = (RadioGroup) findViewById(R.id.main_rg);
        rbRecharge = (RadioButton) findViewById(R.id.main_rbRecharge);
        rbRecharge.setChecked(true);

    }

    private void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.main_rbRecharge:
                        setTitle(getString(R.string.recharge));
                        setLeftTitle("筛选");
                        setRightTitle("列表");
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_rbOrder:
                        setTitle(getString(R.string.order));
                        setLeftTitle("");
                        setRightTitle("");
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.main_rbMine:
                        setTitle(getString(R.string.mine));
                        setLeftTitle("");
                        setRightTitle("消息");
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    public void initData(){
        rechargeFragment = new RechargeFragment();
        orderFragment = new OrderFragment();
        mineFragment = new MineFragment();
        listFrg.add(rechargeFragment);
        listFrg.add(orderFragment);
        listFrg.add(mineFragment);
        viewPager.setAdapter(fpa);
    }
    FragmentPagerAdapter fpa = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return listFrg.get(position);
        }
        @Override
        public int getCount() {
            return listFrg.size();
        }
    };

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
    }

    @Override
    public void permissionFail(int requestCode) {
        super.permissionFail(requestCode);
    }
}
