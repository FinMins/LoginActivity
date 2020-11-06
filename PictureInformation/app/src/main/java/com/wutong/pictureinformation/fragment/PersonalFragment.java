package com.wutong.pictureinformation.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wutong.pictureinformation.CollectActivity;
import com.wutong.pictureinformation.R;



public class PersonalFragment extends Fragment {

    private View view;
    private ListView lvPersonal;
    private TextView tvUsername;
    private String[] strings=new String[]{"收藏文章","收藏图片"};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal,container,false);
        initView();
        return view;
    }

    private void initView(){
        lvPersonal=view.findViewById(R.id.lv_personal);
        tvUsername=view.findViewById(R.id.tv_username);
        String username="1";
        tvUsername.setText(username);
        lvPersonal.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings));//设置适配器
        lvPersonal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String s = strings[position];
                Toast.makeText(getActivity(),s+"-内容",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(), CollectActivity.class);
                int flag=0;
                switch (s){
                    case "收藏文章":
                        flag=1;
                        break;
                    case"收藏图片":
                        flag=2;
                        break;
                    default:
                }
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });
    }

}
