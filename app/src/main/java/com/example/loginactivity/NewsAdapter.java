package com.example.loginactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.ViewHolder>  {
        private List<News> mNewsList;
    public NewsAdapter(List<News> mNewsList){
        this.mNewsList = mNewsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitems,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.authorview.setText(news.getAuthor());
        holder.timeview.setText(news.getTime());
        holder.biaotiview.setText(news.getBiaoti());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
TextView biaotiview;
        TextView authorview;
        TextView timeview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            biaotiview = itemView.findViewById(R.id.bioati);
            authorview = itemView.findViewById(R.id.author);
            timeview = itemView.findViewById(R.id.time);
        }
    }


    
}
