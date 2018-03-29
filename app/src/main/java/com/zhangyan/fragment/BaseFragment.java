package com.zhangyan.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/29.
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutRes(), container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public abstract int getLayoutRes();

    public abstract void initView();

    public abstract void initData();

    public View getView(int resId) {
        return mRootView.findViewById(resId);
    }

    public View getRootView() {
        return mRootView;
    }


}
