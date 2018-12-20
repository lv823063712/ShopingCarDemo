package soexample.umeng.com.shoppingtrolley;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import soexample.umeng.com.shoppingtrolley.adapter.MyAdapter2;
import soexample.umeng.com.shoppingtrolley.base.BaseActivity;
import soexample.umeng.com.shoppingtrolley.bean.MySetData;
import soexample.umeng.com.shoppingtrolley.ipresenter.IPersenterImpl3;
import soexample.umeng.com.shoppingtrolley.iview.IView;

public class Main2Activity extends BaseActivity implements IView, View.OnClickListener {


    private CheckBox Check_All;
    private TextView All_Price;
    private TextView Go_To_Js;
    private RelativeLayout buttom_layout;
    private ExpandableListView Expand_View;
    private MyAdapter2 myAdapter2;
    private IPersenterImpl3 iPersenterImpl3;
    private String myUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private ArrayList<MySetData.DataBean> datas = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        Check_All = findViewById(R.id.Check_All);
        All_Price = findViewById(R.id.All_Price);
        Go_To_Js = findViewById(R.id.Go_To_Js);
        Expand_View = findViewById(R.id.Expand_View);
        //去掉自带的小箭头
        Expand_View.setGroupIndicator(null);

    }

    @Override
    protected void setOnClick() {
        Check_All.setOnClickListener(this);
    }

    @Override
    protected void proLogic() {
        iPersenterImpl3 = new IPersenterImpl3(this);
        myAdapter2 = new MyAdapter2(datas, this);
        Expand_View.setAdapter(myAdapter2);
        iPersenterImpl3.startIPersenter(myUrl);
        myAdapter2.setCallBack(new MyAdapter2.AdapterCallBack() {
            @Override
            public void setGroupCheck(int groupPosition) {
                //判断是否被全选中
                boolean childChecked = myAdapter2.isChildChecked(groupPosition);
                myAdapter2.isChecked(groupPosition, !childChecked);
                myAdapter2.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setChildCheck(int groupPosition, int childPosition) {
                //得到你要点击的商品的复选框
                boolean childChecked = myAdapter2.isChildChecked(groupPosition, childPosition);
                myAdapter2.setChildChecked(groupPosition, childPosition, !childChecked);
                myAdapter2.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setNumber(int groupPosition, int childPosition, int number) {
                //得到你要点击的商品的复选框
                myAdapter2.setShopingNumber(groupPosition, childPosition, number);
                myAdapter2.notifyDataSetChanged();
                flushBottomLayout();
            }
        });

    }

    private void flushBottomLayout() {
        boolean allGoods = myAdapter2.isAllGoods();
        Check_All.setChecked(allGoods);
        //调用适配器的数据计算方法
        float allGoodsPrice = myAdapter2.getAllGoodsPrice();
        int allGoodsNumber = myAdapter2.getAllGoodsNumber();
        All_Price.setText("价格" + allGoodsPrice);
        Go_To_Js.setText("去结算" + allGoodsNumber);

    }

    @Override
    public void success(Object data) {
        MySetData mySetData = (MySetData) data;
        datas.addAll(mySetData.getData());
        myAdapter2.notifyDataSetChanged();

    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Check_All:
                boolean checkAll = myAdapter2.isAllGoods();
                myAdapter2.isAllGoodsChecked(!checkAll);
                myAdapter2.notifyDataSetChanged();
                //适配器刷新
                flushBottomLayout();
                break;
        }
    }
}
