package com.xiangmu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.xiangmu.R;
import com.xiangmu.adapter.WXJXAdapter;
import com.xiangmu.shiti.ShiTi1;
import com.xiangmu.view.ControlableScrollView;
import com.xiangmu.utils.JieKou;
import com.xiangmu.ui.WXJXweb;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 你猜 on 2018/7/2.
 * 微信精选类
 */

public class ButlerFragment extends Fragment  {

    private View view;
    private int p=1;
    private List<ShiTi1> list1 = new ArrayList<>();
    private RecyclerView relative_wxjx;
    private ControlableScrollView fb_quan;//最外层滑动控件用于监听滑动到底部
    private WXJXAdapter wxjxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wxjx, container, false);
//        控件实例化
        initData();

//        数据请求
        initView();
        return view;
    }

    private void initData() {
        fb_quan = view.findViewById(R.id.fb_quan);
        relative_wxjx = view.findViewById(R.id.relative_wxjx);
    }



    private void initView() {
        fb_quan.setOnScrollListener(new ControlableScrollView.OnScrollListener() {
            boolean isExit = true;
            @Override
            public void onScroll(ControlableScrollView v) {
                //执行到此处，说明ScrollView已经滑动到最后，可进行相应的处理。
                if (isExit) {
                    isExit = false;
                    rmkc(p += 1);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isExit = true; //取消退出
                        }
                    }, 500);
                }
            }
        });
        StaggeredGridLayoutManager staggeredGridLayout = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        relative_wxjx.setLayoutManager(staggeredGridLayout);
        rmkc(1);
    }



    private void rmkc(int a) {
        OkHttpUtils.post().url(JieKou.WXJX).addParams("pno", a+"").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
//                            解析数据
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    JSONArray jsonArray = jsonObject1.getJSONArray("list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        String id = jsonObject2.getString("id");
                        String title = jsonObject2.getString("title");
                        String source = jsonObject2.getString("source");
                        String url = jsonObject2.getString("url");
                        ShiTi1 shiTi1 = new ShiTi1(id, title, source, url);
                        list1.add(shiTi1);
                    }
                    wxjxAdapter=new WXJXAdapter(getActivity(),list1);
                    relative_wxjx.setAdapter(wxjxAdapter);
                    wxjxAdapter.setOnItemClickLinstener(new WXJXAdapter.OnItemClickLinstener() {
                        @Override
                        public void OnItemClick(int position) {
                            Intent intent=new Intent(getActivity(), WXJXweb.class);
                            intent.putExtra("urll",list1.get(position).getUrl());
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


}
