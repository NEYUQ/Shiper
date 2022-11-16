package com.example.bottomnavigationbardeprecated.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationbardeprecated.Model.Order;
import com.example.bottomnavigationbardeprecated.R;

import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {
    private List<Order> mList;
    private IClickReceive mIClickReceive;
    public  interface IClickReceive{
        void ClickReceive(Order order);
        void ClickOpen(Order order);
    }
    public NotiAdapter(List<Order> mList, IClickReceive iClickReceive) {
        this.mList = mList;
        this.mIClickReceive = iClickReceive;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        Order order = mList.get(position);
        if (order == null)
            return;
        holder.tv_name.setText(order.getName());
        holder.tv_address.setText(order.getAddress());
        holder.tv_price.setText(""+order.getPrice());
        holder.btn_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickReceive.ClickReceive(order);
            }
        });
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickReceive.ClickOpen(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        return 0;
    }

    public static class NotiViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_price,tv_address;
        public  Button btn_receive;
        private ImageView img_avatar;
        private CardView cardview;
        public NotiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_address = itemView.findViewById(R.id.tv_address);
            btn_receive = itemView.findViewById(R.id.btn_receive);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}
