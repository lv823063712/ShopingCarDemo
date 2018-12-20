package soexample.umeng.com.shoppingtrolley.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.shoppingtrolley.R;
import soexample.umeng.com.shoppingtrolley.bean.MyData;
import soexample.umeng.com.shoppingtrolley.bean.MySetData;
import soexample.umeng.com.shoppingtrolley.zidingyiview.ZiDingYiView;

public class MyAdapter2 extends BaseExpandableListAdapter {
    private ArrayList<MySetData.DataBean> datas;
    private Context context;

    public MyAdapter2(ArrayList<MySetData.DataBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(groupPosition).getSpus().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.group_list, null);
            groupHolder.MyGroupCB = convertView.findViewById(R.id.Group_CB);
            groupHolder.MyGroupTV = convertView.findViewById(R.id.Group_Name);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }

        groupHolder.MyGroupTV.setText(datas.get(groupPosition).getName());
        boolean childChecked = isChildChecked(groupPosition);
        groupHolder.MyGroupCB.setChecked(childChecked);
        //进行监听
        groupHolder.MyGroupCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行判断是否为空
                if (adapterCallBack != null) {
                    adapterCallBack.setGroupCheck(groupPosition);
                }
            }
        });
        return convertView;
    }

    @Override//创建一个子类视图操作,进行数据优化
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GroupChildViewHolder holder = null;
        if (convertView == null) {
            holder = new GroupChildViewHolder();
            convertView = View.inflate(context, R.layout.childe_item, null);
            holder.mChildCheck = convertView.findViewById(R.id.Child_Check_CB);
            holder.mChildPrice = convertView.findViewById(R.id.Child_price);
            holder.mChildTitle = convertView.findViewById(R.id.Child_title);
            holder.mImage = convertView.findViewById(R.id.Child_Icon);
            holder.ziDingYiView = convertView.findViewById(R.id.JiaJianView);
            convertView.setTag(holder);
        } else {
            holder = (GroupChildViewHolder) convertView.getTag();
        }
        MySetData.DataBean.SpusBean spusBean = datas.get(groupPosition).getSpus().get(childPosition);
        Glide.with(context).load(spusBean.getPic_url()).into(holder.mImage);
        holder.mChildCheck.setChecked(spusBean.isChildChecked());
        holder.mChildTitle.setText(spusBean.getName() + "");
        holder.mChildPrice.setText(spusBean.getSkus().get(0).getPrice() + "");
        holder.ziDingYiView.setNumber(spusBean.getPraise_num());
        //调用子类数组的holder类进行优化
        holder.mChildCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterCallBack != null) {
                    adapterCallBack.setChildCheck(groupPosition, childPosition);
                }
            }
        });
        //进行对自定义加减控件的监听
        holder.ziDingYiView.setOnChange(new ZiDingYiView.onCountChange() {
            @Override
            public void setCount(int count) {
                if (adapterCallBack != null) {
                    adapterCallBack.setNumber(groupPosition, childPosition, count);
                }
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        private CheckBox MyGroupCB;
        private TextView MyGroupTV;
    }

    class GroupChildViewHolder {
        private CheckBox mChildCheck;
        private TextView mChildTitle;
        private ImageView mImage;
        private TextView mChildPrice;
        private ZiDingYiView ziDingYiView;
    }

    //点击小组的checkbox,进行判断
    public void isChecked(int groupPosition, boolean isCheck) {
        MySetData.DataBean dataBean = datas.get(groupPosition);
        List<MySetData.DataBean.SpusBean> spus = dataBean.getSpus();
        for (int i = 0; i < spus.size(); i++) {
            MySetData.DataBean.SpusBean spusBean = spus.get(i);
            spusBean.setChildChecked(isCheck);
        }
    }

    public boolean isChildChecked(int groupPosition) {
        boolean boo = true;
        MySetData.DataBean bean = datas.get(groupPosition);
        List<MySetData.DataBean.SpusBean> spus = bean.getSpus();
        for (int i = 0; i < spus.size(); i++) {
            MySetData.DataBean.SpusBean spusBean = spus.get(i);
            if (!spusBean.isChildChecked()) {
                return false;
            }
        }
        return boo;
    }

    //点击child给他进行赋值    给最下级的列表进行赋值
    public void setChildChecked(int groupPosition, int childPosition, boolean isCheckBox) {
        MySetData.DataBean.SpusBean spusBean = datas.get(groupPosition).getSpus().get(childPosition);
        //进行判断
        spusBean.setChildChecked(isCheckBox);
    }

    //查看当前这个商品有没有被选中
    public boolean isChildChecked(int groupPosition, int childPosition) {
        MySetData.DataBean.SpusBean spusBean = datas.get(groupPosition).getSpus().get(childPosition);

        if (spusBean.isChildChecked()) {
            return true;
        }
        return false;
    }

    //给商品数量进行赋值
    public void setShopingNumber(int groupPosition, int childPosition, int number) {
        MySetData.DataBean.SpusBean spusBean = datas.get(groupPosition).getSpus().get(childPosition);
        spusBean.setPraise_num(number);
    }

    //因为底部视图有一个全选反选所以进行监听
    public boolean isAllGoods() {
        boolean boo = true;
        for (int i = 0; i < datas.size(); i++) {
            MySetData.DataBean dataBean = datas.get(i);
            for (int j = 0; j < dataBean.getSpus().size(); j++) {
                MySetData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (!spusBean.isChildChecked()) {
                    boo = false;
                }
            }
        }
        return boo;
    }

    //实现全选反选的功能
    public void isAllGoodsChecked(boolean isAllChecked) {
        for (int i = 0; i < datas.size(); i++) {
            MySetData.DataBean dataBean = datas.get(i);
            for (int j = 0; j < dataBean.getSpus().size(); j++) {
                MySetData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                spusBean.setChildChecked(isAllChecked);
            }
        }
    }

    //计算所有商品的价格
    public float getAllGoodsPrice() {
        float allPrice = 0;
        for (int i = 0; i < datas.size(); i++) {
            MySetData.DataBean dataBean = datas.get(i);
            for (int j = 0; j < dataBean.getSpus().size(); j++) {
                MySetData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                //进行判断
                if (spusBean.isChildChecked()) {
                    allPrice += spusBean.getPraise_num() * Float.parseFloat(spusBean.getSkus().get(0).getPrice());
                }
            }
        }
        return allPrice;
    }

    //计算所有商品的总数
    public int getAllGoodsNumber() {
        int numBer = 0;
        for (int i = 0; i < datas.size(); i++) {
            MySetData.DataBean dataBean = datas.get(i);
            for (int j = 0; j < dataBean.getSpus().size(); j++) {
                MySetData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                //进行判断
                if (spusBean.isChildChecked()) {
                    numBer += spusBean.getPraise_num();
                }
            }
        }
        return numBer;
    }

    //因为点击Group和Child第CheckBox在主页面都需要刷新值所以做成接口回调
    public interface AdapterCallBack {

        void setGroupCheck(int groupPosition);

        void setChildCheck(int groupPosition, int childPosition);

        void setNumber(int groupPosition, int childPosition, int number);
    }

    private AdapterCallBack adapterCallBack;

    //设定一个接口回调
    public void setCallBack(AdapterCallBack callBack) {
        this.adapterCallBack = callBack;
    }

    ;

}
