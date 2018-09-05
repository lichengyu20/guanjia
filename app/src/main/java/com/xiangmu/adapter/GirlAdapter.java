package com.xiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiangmu.R;
import com.xiangmu.shiti.ShiTi2;

import java.util.ArrayList;
import java.util.List;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.MyHolder>{

    private Context context;
    private List<ShiTi2> list;

    public GirlAdapter(Context context, List<ShiTi2> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GirlAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyHolder myHolder=new MyHolder(LayoutInflater.from(context).inflate(R.layout.girl_adapter, null));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GirlAdapter.MyHolder myHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {


        public MyHolder(@NonNull View itemView) {
            super(itemView);

        }
    }


    //    适配器的点击事件
    public interface OnItemClickLinstener {
        void OnItemClick(int position);
    }
    private OnItemClickLinstener mOnItemClickLinstener;
    public void setOnItemClickLinstener(OnItemClickLinstener onItemClickLinstener) {
        mOnItemClickLinstener = onItemClickLinstener;
    }


}
