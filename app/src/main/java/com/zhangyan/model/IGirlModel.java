package com.zhangyan.model;

import com.zhangyan.bean.Girl;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 *  主要加载数据 业务逻辑
 */

public interface IGirlModel {

    //加载数据
    void loadGirl(GirlLoadListener listener);

    interface GirlLoadListener {
        void onComplete(List<Girl> girls);
    }


}
