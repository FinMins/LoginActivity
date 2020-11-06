package com.wutong.pictureinformation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wutong.pictureinformation.R;
import com.wutong.pictureinformation.adapter.PictureRecyclerViewAdapter;
import com.wutong.pictureinformation.controller.PictureCollectController;
import com.wutong.pictureinformation.entity.Picture;
import com.wutong.pictureinformation.entity.PictureCollect;
import com.wutong.pictureinformation.util.GridItemDivider;
import com.wutong.pictureinformation.util.HttpUtil;
import com.wutong.pictureinformation.util.ParseJsonWithJsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PictureFragment extends Fragment {
    private static final String TAG="PictureFragment";
    private View view;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private PictureRecyclerViewAdapter pictureRecyclerViewAdapter;
    private final static int GET_PICTURES=1;
    private final static int NO_MORE=2;

//    https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
    private String url="https://gank.io/api/v2/data/category/Girl/type/Girl/page/";
    int page=1;
    int size=10;
    private List<Picture> pictureList=new ArrayList<>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case GET_PICTURES:
                    pictureRecyclerViewAdapter.notifyDataSetChanged();//是通过Handler 设置notifyDataSetChanged方式来动态更新recyclerView
                    break;
                case NO_MORE:
                    Toast.makeText(getContext(),"没有更多资源了",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_picture,container,false);
        initView();
        initData();
        return view;
    }

    private void initView() {
    //    getPictures();
        refreshLayout=view.findViewById(R.id.srl_pic);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));//设置2列
        pictureRecyclerViewAdapter=new PictureRecyclerViewAdapter(getContext(),pictureList);
        //添加分隔线 只针对于LinearLayout视图
/*        DividerItemDecoration divider=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.divider_shape));
        recyclerView.addItemDecoration(divider);*/
        GridItemDivider divider=new GridItemDivider(getContext());
        recyclerView.addItemDecoration(divider);//添加分隔线
        recyclerView.setAdapter(pictureRecyclerViewAdapter);//设置适配器

        //上拉加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pictureList.clear();
                page=page+1;
                getPictures();
                refreshLayout.finishLoadMore(true);
            }
        });

        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pictureList.clear();
                queryFromServer(url+page+"/count/"+size);
                refreshLayout.finishRefresh(true);
            }
        });
    }


    private void initData(){
        Bundle bundle = getArguments();
        String type =null;
        if (bundle!=null){
            type = bundle.getString("type");
        }
        if (type!=null && type.equals("collect")){
            pictureList.clear();
//            PictureCollectController pictureCollectController=new PictureCollectController(getContext());
//            PictureCollect pictureCollect=new PictureCollect();
//            String pictureUrl=pictureCollect.getUrl();
//            Log.d("PictureFragment", "n");
//            List<Picture> pictures=pictureCollectController.findCollect(pictureUrl);
//            pictureList.addAll(pictures);
//          /*  List<PictureCollect> pictureCollectList= pictureCollectController.findSelected(pictureUrl);*/
//            refreshLayout.setEnableLoadMore(false);
//            refreshLayout.setEnableRefresh(false);
//            pictureRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            getPictures();
        }
    }

    //加载图片
    private void getPictures() {
        List<Picture> pictures=new ArrayList<>();
        if (pictures.size()>0&& pictures.size()==size){
            pictureList.addAll(pictures);
            pictureRecyclerViewAdapter.notifyDataSetChanged();
        }
        else {
            queryFromServer(url+page+"/count/"+size);
        }
    }

    /**
     * 从服务器获取数据
     */
    private void queryFromServer(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {//对异常情况进行处理
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {//得到服务器返回数据的具体内容
                String responseData=response.body().string();
                pictureList.clear();
                List<Picture> pictures = new ArrayList<>();
                pictures.clear();
                pictures= ParseJsonWithJsonObject.getPictures(responseData);//将服务器获取的数据解析
                if(pictures.size()>0){
                    pictureList.addAll(pictures);
                    handler.sendEmptyMessage(GET_PICTURES);
                }else {
                    handler.sendEmptyMessage(NO_MORE);
                }
            }
        });
    }
}
