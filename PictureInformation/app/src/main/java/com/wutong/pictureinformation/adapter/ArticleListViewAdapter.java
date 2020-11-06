package com.wutong.pictureinformation.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wutong.pictureinformation.R;
import com.wutong.pictureinformation.entity.Article;


import java.util.List;

public class ArticleListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Article> articleList;
    private int resourceId;

    @Override
    public int getCount() {
        return  articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArticleListViewAdapter(@NonNull Context context, int resource, List<Article> articleList) {
        this.mContext=context;
        this.articleList=articleList;
        this.resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article=articleList.get(position);
        View view;
        //将之前加载好的布局缓存
        if (convertView==null){
            view=LayoutInflater.from(mContext).inflate(resourceId,parent,false);
        }else {
            view=convertView;
        }

        String title=article.getTitle();
        String author=article.getAuthor();
        String publishAt=article.getPublishedAt();
        TextView tvTitle=view.findViewById(R.id.tv_title);
        TextView tvAuthor=view.findViewById(R.id.tv_author);
        TextView tvPublishAt=view.findViewById(R.id.tv_publishAt);
        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvPublishAt.setText(publishAt);
        return view;
    }


/*    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    }*/
}
