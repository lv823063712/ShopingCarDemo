package soexample.umeng.com.shoppingtrolley.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import soexample.umeng.com.shoppingtrolley.Main2Activity;
import soexample.umeng.com.shoppingtrolley.R;
import soexample.umeng.com.shoppingtrolley.bean.ceshi;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private ArrayList<ceshi.DataBean> datas;
    private Context mcontext;
    private ItemOnClickCallBack itemOnClickCallBack;
    private View inflate;

    public RestaurantAdapter(ArrayList<ceshi.DataBean> datas) {
        this.datas = datas;

    }

    //点击事件回调接口
    public void setOnClickCallBack(ItemOnClickCallBack onClickCallBack) {
        this.itemOnClickCallBack = onClickCallBack;
    }

    public interface ItemOnClickCallBack {
        void onItemClick(int pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.mcontext = viewGroup.getContext();
        inflate = LayoutInflater.from(mcontext).inflate(R.layout.linear_item, viewGroup, false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(datas.get(i).getNews_title());
        viewHolder.tip.setText(datas.get(i).getNews_summary());
        Glide.with(mcontext).load(datas.get(i).getPic_url()).into(viewHolder.iv);

        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, Main2Activity.class);
                mcontext.startActivity(intent);
            }
        });

        viewHolder.tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, Main2Activity.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;
        private TextView tip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.My_GoodsImg);
            tv = itemView.findViewById(R.id.My_GoodsTitle);
            tip = itemView.findViewById(R.id.My_GoodsTip);

        }
    }


}
