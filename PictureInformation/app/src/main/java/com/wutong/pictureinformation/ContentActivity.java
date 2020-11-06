package com.wutong.pictureinformation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wutong.aidlserver.IMyAidlInterface;
import com.wutong.pictureinformation.controller.ArticleCollectController;
import com.wutong.pictureinformation.controller.PictureCollectController;
import com.wutong.pictureinformation.entity.Article;
import com.wutong.pictureinformation.entity.ArticleCollect;
import com.wutong.pictureinformation.entity.PictureCollect;
import com.wutong.pictureinformation.util.HttpUtil;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvContentTitle,tvContentAuthor,tvContentInfo;
    private ImageView ivCollect;
    private Article article;
    private String responseData;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==1){
                tvContentInfo.setText(Html.fromHtml(responseData));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initView();
        initData();
    }

    private void initData() {
        //获取ArticleFragment中传递过来的intent的数据
        Log.d("nn","initData");
        article = getIntent().getParcelableExtra("article");
        //进行数据获取
        tvContentTitle.setText(article.getTitle());
        tvContentAuthor.setText(article.getAuthor());
        String url=article.getContent();
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                responseData=response.body().string();
                Log.d( "onResponse: ",responseData);
                if (responseData!=null){
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
            }
        });
    }

    private void initView() {
        tvContentTitle=findViewById(R.id.tv_content_title);
        tvContentAuthor=findViewById(R.id.tv_content_author);
        tvContentInfo=findViewById(R.id.tv_content_info);
        ivCollect=findViewById(R.id.iv_img_collect);
        ivCollect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_img_collect:
                ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.collect));

                /*ArticleCollect articleCollect=new ArticleCollect();
                articleCollect.setTitle(articleCollect.getTitle());
                articleCollect.setAuthor(articleCollect.getAuthor());
                articleCollect.setPublishedAt(articleCollect.getPublishedAt());
                Log.d("ContentActivity", article.getTitle());
                ArticleCollectController articleCollectController=new  ArticleCollectController(this);
                boolean flag=articleCollectController.collectArticle(articleCollect);
                if (flag){
                    Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"收藏失败",Toast.LENGTH_SHORT).show();
                }*/

                Toast.makeText(this,"恭喜您，收藏成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
