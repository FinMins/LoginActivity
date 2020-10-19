package com.example.loginactivity;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentAdpter  extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments ;

    public MyFragmentAdpter(@NonNull FragmentManager fm,ArrayList<Fragment> fragments, int behavior) {
        super(fm, behavior);
        this.fragments = fragments ;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    //
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);

    }
}
