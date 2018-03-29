package com.zhangyan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zhangyan.bean.Girl;
import com.zhangyan.rxjava.R;
import com.zhangyan.presenter.GirlPresenterV1;
import com.zhangyan.view.IGirlView;

import java.util.ArrayList;
import java.util.List;

public class GirlMVPActivity extends AppCompatActivity implements IGirlView{

    private RecyclerView mRecycleView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_mvp);
        mRecycleView = findViewById(R.id.main_rv);

        //中间者（Presenter）加载数据
        new GirlPresenterV1(this).bind();
    }

    @Override
    public void showLoading() {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showGirls(List<Girl> girls) {
        mAdapter = new RecyclerViewAdapter(girls);
        //加载适配器
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setAdapter(mAdapter);
    }
}
