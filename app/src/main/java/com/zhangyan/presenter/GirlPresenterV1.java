package com.zhangyan.presenter;

import com.zhangyan.bean.Girl;
import com.zhangyan.model.GirlModelImplV1;
import com.zhangyan.model.IGirlModel;
import com.zhangyan.view.IGirlView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 * girl presenter
 *
 *  需要持有View 和 model
 */

public class GirlPresenterV1 {

    /**
     * 通过构造方法实例化IGirlView
     * @param mGirlView
     */
    public GirlPresenterV1(IGirlView mGirlView) {
        super();
        this.mGirlView = mGirlView;
    }

    //view的接口
    IGirlView mGirlView;

    //Model的接口
    IGirlModel mGirlModel = new GirlModelImplV1();

    /**
     * bing view and model
     * 绑定
     */
    public void bind(){

        mGirlView.showLoading();


        //让model去加载数据
        if (mGirlModel!=null){
            mGirlModel.loadGirl(new IGirlModel.GirlLoadListener() {
                @Override
                public void onComplete(List<Girl> girls) {
                    //把数据传给View做显示处理
                    mGirlView.showGirls(girls);
                }
            });
        }
    }
    public void setmGirlModel(IGirlModel mGirlModel){
        this.mGirlModel = mGirlModel;

    }

}
