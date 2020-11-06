package com.example.loginactivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    private RecyclerView recyclerView ;
    private NewsAdapter newsAdapter;
    private List<News> newsList=new ArrayList<>();
    public NewsFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         init();
    }

    private void init(){
        recyclerView = getView().findViewById(R.id.newsrecyclerview);
        recyclerView = getView().findViewById(R.id.newsrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter  = new NewsAdapter(newsList);
    }

    //获取文章数据
}