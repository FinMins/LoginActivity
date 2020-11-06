package com.wutong.pictureinformation.adapter;

import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class MyFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String []strings=new String []{"文章","图片","个人中心"};
    public MyFragmentAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list=list;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    //getPageTitle方法会返回position位置的PagerTab的标题
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
