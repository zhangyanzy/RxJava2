package com.zhangyan.view;

import com.zhangyan.bean.Girl;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface IGirlView {


    void showLoading();

    void showGirls(List<Girl> girls);
}
