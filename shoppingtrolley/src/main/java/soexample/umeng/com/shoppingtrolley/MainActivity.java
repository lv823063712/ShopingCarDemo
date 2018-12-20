package soexample.umeng.com.shoppingtrolley;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import soexample.umeng.com.shoppingtrolley.adapter.MyAdapter;
import soexample.umeng.com.shoppingtrolley.adapter.RestaurantAdapter;
import soexample.umeng.com.shoppingtrolley.base.BaseActivity;
import soexample.umeng.com.shoppingtrolley.bean.MyGroupData;
import soexample.umeng.com.shoppingtrolley.bean.ceshi;
import soexample.umeng.com.shoppingtrolley.ipresenter.IPersenterImpl;
import soexample.umeng.com.shoppingtrolley.ipresenter.IPersenterImpl2;
import soexample.umeng.com.shoppingtrolley.iview.IView;
import soexample.umeng.com.shoppingtrolley.iview.IView2;

public class MainActivity extends BaseActivity implements IView, IView2 {


    private XRecyclerView MyRecy;
    private XRecyclerView MyXRecy;
    private MyAdapter myAdapter;
    private String mUrl = "https://gank.io/api/data/福利/10/1";
    private String oneUrl = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private ArrayList<MyGroupData.ResultsBean> datas = new ArrayList<>();
    private ArrayList<ceshi.DataBean> lists = new ArrayList<>();
    private IPersenterImpl iPersenter;
    private RestaurantAdapter restaurantAdapter;
    private IPersenterImpl2 iPersenterImpl2;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        MyRecy = findViewById(R.id.MyRecy);
        MyXRecy = findViewById(R.id.MyXRecy);
        //九宫格布局视图
        GridLayoutManager manager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        MyRecy.setLayoutManager(manager);
        myAdapter = new MyAdapter(datas);
        MyRecy.setAdapter(myAdapter);
        //垂直式视图
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        MyXRecy.setLayoutManager(manager1);
        restaurantAdapter = new RestaurantAdapter(lists);
        MyXRecy.setAdapter(restaurantAdapter);


    }

    @Override
    protected void setOnClick() {
    }

    @Override
    protected void proLogic() {
        iPersenter = new IPersenterImpl(this);
        iPersenterImpl2 = new IPersenterImpl2(this);
        iPersenter.startIPersenter(mUrl);
        iPersenterImpl2.startIPersenter(oneUrl);
    }

    @Override
    public void success(Object data) {
        MyGroupData myGroupData = (MyGroupData) data;
        datas.addAll(myGroupData.getResults());
        //刷新适配器
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {
    }

    @Override
    public void success2(Object data) {
        ceshi ceshi1 = (ceshi) data;
        lists.addAll(ceshi1.getData());
        restaurantAdapter.notifyDataSetChanged();
    }

    @Override
    public void error2(Object error) {

    }
}
