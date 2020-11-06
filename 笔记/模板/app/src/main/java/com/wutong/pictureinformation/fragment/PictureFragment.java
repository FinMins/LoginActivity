package com.wutong.pictureinformation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wutong.pictureinformation.R;
import com.wutong.pictureinformation.adapter.PictureRecyclerViewAdapter;
import com.wutong.pictureinformation.entity.Picture;
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
    private PictureRecyclerViewAdapter pictureRecyclerViewAdapter;
    private final static int GET_PICTURES=1;


    private String url="https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10";
    private List<Picture> pictureList=new ArrayList<>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                pictureRecyclerViewAdapter.notifyDataSetChanged();//是通过Handler 设置notifyDataSetChanged方式来动态更新recyclerView
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_picture,container,false);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        queryFromServer(url);
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
                }
            }
        });
    }
}
