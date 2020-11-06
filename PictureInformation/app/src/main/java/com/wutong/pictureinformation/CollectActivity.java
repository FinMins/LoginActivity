package com.wutong.pictureinformation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wutong.pictureinformation.fragment.ArticleFragment;
import com.wutong.pictureinformation.fragment.PictureFragment;

public class CollectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
    }


    /**
     * 初始化 动态添加碎片
     * 1.创建待添加碎片的实例
     * 2.获取FragmentManager
     * 3.开启事务 通过beginTransaction方法开启
     * 4.向容器添加或替换碎片
     * 5.提交事务
     */
    private void initView() {
       int flag=getIntent().getIntExtra("flag",0);//得到传过来的flag值
       Fragment fragment=null;
       FragmentManager fragmentManager=getSupportFragmentManager();
       FragmentTransaction transaction=fragmentManager.beginTransaction();
       Bundle bundle=new Bundle();
       switch (flag){
           case 1:
               fragment=new ArticleFragment();
               break;
           case 2:
               fragment=new PictureFragment();
               break;
       }
       bundle.putString("type","collect");
        if (fragment!=null){
            fragment.setArguments(bundle);
            transaction.replace(R.id.ll_collect,fragment);
            transaction.commit();
        }
    }
}
