package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ShareActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final String TAG = "shareActivity.class";
    private  String usernum  ; //用户账号
    private ViewPager myViewPager ;
    private ArrayList<Fragment> fragments ;
    private MyFragmentAdpter adpter;
    private TextView text1 ;
    private TextView text2 ;
    private TextView text3 ;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private LayoutInflater inflater;
    private RelativeLayout firstItem ;
    private RelativeLayout secondItem ;
    private RelativeLayout thirdItem ;
    private TextView title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        //获取传送过来的账号。
        getUserNum();
        init();
    }



    //获取传送过来的账号。
    private void getUserNum(){
        Intent intent = getIntent();
       usernum =  intent.getStringExtra("usernum");
    }

    //此函数用于碎片fragment获取用户的账号。
    public String getUsernumToFragment(){
        return usernum ;
    }

    private void init(){
        inflater = getLayoutInflater();
        title = findViewById(R.id.titleTop);
        fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new ImgFragment());
        fragments.add(new MyCenterFragment());
        firstItem =findViewById(R.id.first_layout);
        firstItem.setOnClickListener(this);
        secondItem = findViewById(R.id.second_layout);
        secondItem.setOnClickListener(this);
        thirdItem = findViewById(R.id.third_layout);
        thirdItem.setOnClickListener(this);
        text1 = findViewById(R.id.first_text);
        text2 = findViewById(R.id.second_text);
        text3 = findViewById(R.id.third_text);
        img1 = findViewById(R.id.first_image);
        img2 = findViewById(R.id.second_image);
        img3 = findViewById(R.id.third_image);
        myViewPager = findViewById(R.id.myViewPager);
       adpter = new MyFragmentAdpter(getSupportFragmentManager(),fragments,0);
       myViewPager.setAdapter(adpter);
        text1.setTextColor(Color.RED);
       //监听事件.
    myViewPager.addOnPageChangeListener(new MyPagerChangeListener());
    myViewPager.setCurrentItem(0);
    setColor(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_layout:
                myViewPager.setCurrentItem(0);
                setColor(0);
                break;
            case R.id.second_layout:
                myViewPager.setCurrentItem(1);
                setColor(1);
                break;
            case R.id.third_layout:
                myViewPager.setCurrentItem(2);
                setColor(2);
                break;



        }
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        //这个在页面滑动的时候会调用此方法，在滑动停止前会一直调用。

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "在滑动！！！");


        }

        @Override
        //这个方法在页面完成后调用。！！！！！参数为跳转完成后的这个页面
        public void onPageSelected(int position) {
                setColor(position);
        }



        @Override
        /***状态改变时调用 state表示某个正在改变的状态，取值有：
         *  0 ：表示什么都没做
         *  1：表示在滑动
         *  2：滑动完毕了
         * 页面开始滑动时，三种状态顺序为(1,2,0);
         *
         * */
        public void onPageScrollStateChanged(int state) {
            switch (state){
                case 0:
                    Log.d(TAG, "state==0，什么也没做");
                    break;
                case 1:
                    Log.d(TAG, "state==1，正在滑动");
                    break;
                case 2:
                    Log.d(TAG, "state==2，滑动完毕");
                    break;
                default:
                    break;

            }

        }
    }

    private void setColor(int position){
        text1.setTextColor(Color.rgb(117,151,179));
        text2.setTextColor(Color.rgb(117,151,179));
        text3.setTextColor(Color.rgb(117,151,179));
        img1.setBackgroundColor(Color.WHITE);
        img2.setBackgroundColor(Color.WHITE);
        img3.setBackgroundColor(Color.WHITE);
        switch (position){
            case 0 :
                text1.setTextColor(Color.RED);
                img1.setBackgroundColor(Color.RED);
                title.setText("文章");
                break;
            case 1 :
                text2.setTextColor(Color.RED);
                img2.setBackgroundColor(Color.RED);
                title.setText("图片");

                break;
            case 2 :
                text3.setTextColor(Color.RED);
                img3.setBackgroundColor(Color.RED);
                title.setText("我的");

                break;
            default:
                break;

        }

    }
}