package com.wutong.pictureinformation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.wutong.pictureinformation.adapter.MyFragmentAdapter;
import com.wutong.pictureinformation.fragment.ArticleFragment;
import com.wutong.pictureinformation.fragment.PersonalFragment;
import com.wutong.pictureinformation.fragment.PictureFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {


    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private ArrayList fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        myViewPager=findViewById(R.id.myViewPager);
        tabLayout=findViewById(R.id.tabLayout);
        ArticleFragment articleFragment=new ArticleFragment();
        PictureFragment pictureFragment=new PictureFragment();
        PersonalFragment personalFragment=new PersonalFragment();
        fragments=new ArrayList<Fragment>();
        fragments.add(articleFragment);//将fragment添加到list集合中
        fragments.add(pictureFragment);
        fragments.add(personalFragment);
        FragmentManager fragmentManager=getSupportFragmentManager();//获取FragmentManager
        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(fragmentManager,fragments);
        myViewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(myViewPager);//把TabLayout和ViewPager组合,使用setupWithViewPager可以让TabLayout和ViewPager联动




    }
}