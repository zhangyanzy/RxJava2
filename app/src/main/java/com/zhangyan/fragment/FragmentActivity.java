package com.zhangyan.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.zhangyan.rxjava.R;

public class FragmentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , ViewPager.OnPageChangeListener
        , View.OnTouchListener
    ,HomeFragment.OnFragmentInteractionListener
    ,DiscoverFragment.OnFragmentInteractionListener
{


    private ViewPager mViewPage;
    private MenuItem mMenuItem;
    private BottomNavigationView mBottomNavigationView;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mViewPage = findViewById(R.id.main_vp);
        mViewPage.addOnPageChangeListener(this);
        mViewPage.setOnTouchListener(this);
        mBottomNavigationView = findViewById(R.id.bottom_view);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new HomeFragment());
        mAdapter.addFragment(new DiscoverFragment());
        mViewPage.setAdapter(mAdapter);
        mViewPage.setOffscreenPageLimit(3);
        mBottomNavigationView.setSelectedItemId(R.id.item_home);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                mViewPage.setCurrentItem(0);
                break;
            case R.id.item_discover:
                mViewPage.setCurrentItem(1);
                break;
            default:
                break;
        }
        return true;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mMenuItem != null) {
            mMenuItem.setCheckable(false);
        } else {
            mBottomNavigationView.getMenu().getItem(0).setCheckable(false);
        }
        mMenuItem = mBottomNavigationView.getMenu().getItem(position);
        mMenuItem.setCheckable(true);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }



    @Override
    public void onFragmentInteraction(String uri) {

    }


}

