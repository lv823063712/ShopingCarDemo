package soexample.umeng.com.shoppingtrolley.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import soexample.umeng.com.shoppingtrolley.R;
import soexample.umeng.com.shoppingtrolley.bean.MyGroupData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<MyGroupData.ResultsBean> datas;
    private Context context;
    private View v;

    public MyAdapter(ArrayList<MyGroupData.ResultsBean> datas) {
        this.datas = datas;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        v = LayoutInflater.from(context).inflate(R.layout.linitem, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tV.setText(datas.get(i).getType());
        String url = datas.get(i).getUrl();
        String replace = url.replace("https", "http");
        Glide.with(context).load(replace).into(viewHolder.iV);




    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iV;
        private TextView tV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iV = itemView.findViewById(R.id.My_Img);
            tV = itemView.findViewById(R.id.My_Title);

        }
    }


}
