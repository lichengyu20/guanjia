package com.xiangmu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangmu.R;
import com.xiangmu.adapter.GirlAdapter;
import com.xiangmu.shiti.ShiTi2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你猜 on 2018/7/2.
 */

public class GirlFragment extends Fragment{

    private RecyclerView girl_recycler;
    private View view;
    private List<ShiTi2> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_girl, container, false);
//        控件实例化
        initView();
        initData();
        return view;
    }

    private void initView() {
        girl_recycler=view.findViewById(R.id.girl_recycler);
    }


    private void initData() {


        GirlAdapter girlAdapter=new GirlAdapter(getActivity(),list);
        girl_recycler.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        girl_recycler.setAdapter(girlAdapter);
        girlAdapter.setOnItemClickLinstener(new GirlAdapter.OnItemClickLinstener() {
            @Override
            public void OnItemClick(int position) {
            }
        });

    }



}
