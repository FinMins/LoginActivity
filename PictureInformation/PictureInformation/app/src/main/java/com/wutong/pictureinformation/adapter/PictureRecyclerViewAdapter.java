package com.wutong.pictureinformation.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wutong.pictureinformation.R;
import com.wutong.pictureinformation.entity.Picture;

import java.util.List;


public class PictureRecyclerViewAdapter extends RecyclerView.Adapter<PictureRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Picture> pictureList;

    public PictureRecyclerViewAdapter(Context mContext, List<Picture> pictureList) {
        this.mContext = mContext;
        this.pictureList = pictureList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_list_item,parent,false);//创建view实例,将布局传入
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imageView.setImageResource( );//对RecyclerView的子项进行赋值
        final Picture picture=pictureList.get(position);
        String picUrl=picture.getUrl();
        Log.d("图片地址",picture.getUrl());
        Glide.with(mContext)
                .load(picUrl)
                .into(holder.imageView);//对RecyclerView的子项进行赋值 将图片设置进去
        holder.itemView.setOnClickListener(new View.OnClickListener() {//给每个图片设置点击事件
            @Override
            public void onClick(View v) {
                checkPicture(picture);
            }
        });
    }

    private void checkPicture(final Picture picture) {
        // 将布局文件转换成View对象， contentView内容视图
        View contentView =LayoutInflater.from(mContext).inflate(R.layout.picture_check_item,null);
        DisplayMetrics dm=mContext.getResources().getDisplayMetrics();
        int height=dm.heightPixels;
        int width=dm.widthPixels;
        // 将转换的View放置到 新建一个popupWindow对象中
        final PopupWindow popupWindow=new PopupWindow(contentView,width-300,height-900);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//点击外部让它消失
        popupWindow.setContentView(contentView);

        ImageView ivCheckPicture=contentView.findViewById(R.id.iv_check_picture);
        Button btnDownloadPic=contentView.findViewById(R.id.btn_download_pic);
        Button btnCollectPic=contentView.findViewById(R.id.btn_collect_pic);


        Glide.with(contentView).load(picture.getUrl()).into(ivCheckPicture);//将图片加载进去
        Log.d("bbb", picture.getUrl());
        View rootView=LayoutInflater.from(mContext).inflate(R.layout.activity_main,null);
        popupWindow.showAtLocation(rootView, Gravity.CENTER,0,0);//设置显示的形式 在中间显示

        ivCheckPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();//关闭弹窗
            }
        });

        btnDownloadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"您点击了下载图片",Toast.LENGTH_SHORT).show();
            }
        });
        btnCollectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"您点击了收藏图片",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{//定义内部类 ViewHolder
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);//获取picture_list_item布局中的imageView实例
        }
    }
}
