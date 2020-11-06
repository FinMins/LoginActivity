package com.wutong.pictureinformation.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wutong.pictureinformation.ContentActivity;
import com.wutong.pictureinformation.R;
import com.wutong.pictureinformation.entity.Article;

import java.util.List;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Article> articleList;

    public ArticleRecyclerViewAdapter(Context mContext, List<Article> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article=articleList.get(position);
        String title=article.getTitle();
        String author=article.getAuthor();
        String publishAt=article.getPublishedAt();
        Log.d("标题：",article.getTitle());
        Log.d("作者：",article.getAuthor());
        Log.d("出版日期：",article.getPublishedAt());
        holder.tvTitle.setText(title);
        holder.tvAuthor.setText(author);
        holder.tvPublishAt.setText(publishAt);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ContentActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle,tvAuthor,tvPublishAt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvAuthor=itemView.findViewById(R.id.tv_author);
            tvPublishAt=itemView.findViewById(R.id.tv_publishAt);
        }
    }
}
