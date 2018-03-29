package com.zhangyan.fragment;

import com.zhangyan.rxjava.R;

/**
 * Created by Administrator on 2018/3/29.
 */

public class HomeFragment extends BaseFragment {

    @Override
    public int getLayoutRes() {
        return R.layout.home_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    /**
     * fragment的通信
     */
    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(String ur);
    }
}
