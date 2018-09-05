package com.xiangmu.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiangmu.R;
import com.xiangmu.shiti.ShiTi1;

import java.util.ArrayList;
import java.util.List;

public class WXJXAdapter extends RecyclerView.Adapter<WXJXAdapter.MyHolder>{

    private Context context;
    private List<ShiTi1> list;

    public WXJXAdapter(Context context, List<ShiTi1> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WXJXAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyHolder holder=new MyHolder(LayoutInflater.from(context).inflate(R.layout.wxjx_adapter,viewGroup,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final WXJXAdapter.MyHolder myHolder, int i) {
        myHolder.wxadapter_title1.setText(list.get(i).getTitle());
        myHolder.wxadapter_title2.setText(list.get(i).getSource());
        if(mOnItemClickLinstener!=null) {
            myHolder.wxadapter_relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i1=myHolder.getAdapterPosition();
                    mOnItemClickLinstener.OnItemClick(i1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView wxadapter_title1,wxadapter_title2;
        private RelativeLayout wxadapter_relative;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            wxadapter_title1=itemView.findViewById(R.id.wxadapter_title1);
            wxadapter_title2=itemView.findViewById(R.id.wxadapter_title2);
            wxadapter_relative=itemView.findViewById(R.id.wxadapter_relative);

        }
    }

    public interface OnItemClickLinstener {
        void OnItemClick(int position);
    }

    private OnItemClickLinstener mOnItemClickLinstener;

    public void setOnItemClickLinstener(OnItemClickLinstener onItemClickLinstener) {
        mOnItemClickLinstener = onItemClickLinstener;
    }

}
