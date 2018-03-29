package com.zhangyan.model;

import com.zhangyan.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 * girl model implementation version one
 * 数据加载的实现类
 *
 */

public class GirlModelImplV1 implements IGirlModel {
    private List<Girl> data;


    @Override
    public void loadGirl(GirlLoadListener listener) {
//
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Girl("zhang","five"));
        }
        //回调监听
        listener.onComplete(data);
    }

}
