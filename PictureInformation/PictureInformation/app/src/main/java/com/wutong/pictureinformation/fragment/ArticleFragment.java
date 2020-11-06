package com.wutong.pictureinformation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wutong.pictureinformation.R;
import com.wutong.pictureinformation.adapter.ArticleRecyclerViewAdapter;
import com.wutong.pictureinformation.entity.Article;
import com.wutong.pictureinformation.util.HttpUtil;
import com.wutong.pictureinformation.util.ParseJsonWithJsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ArticleFragment extends Fragment {

    private RecyclerView recyclerView;
    private View view;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    private List<Article> articleList=new ArrayList<>();
    private String url="https://gank.io/api/v2/data/category/GanHuo/type/Android/page/1/count/10";

    private  final static int GET_SUCCESS=1;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==1){
                articleRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_article,container,false);//加载布局
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        queryFromServer(url);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articleRecyclerViewAdapter=new ArticleRecyclerViewAdapter(getContext(),articleList);
        DividerItemDecoration divider=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.divider_shape));
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(articleRecyclerViewAdapter);
    }

    private void queryFromServer(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData=response.body().string();
                articleList.clear();
                List<Article> articles=new ArrayList<>();
                articles.clear();
                articles=ParseJsonWithJsonObject.getArticles(responseData);//将服务器获取的数据解析
                if (articles!=null){
                    articleList.addAll(articles);
                    handler.sendEmptyMessage(GET_SUCCESS);
                }
            }
        });
    }
}
