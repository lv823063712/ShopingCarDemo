package soexample.umeng.com.shoppingtrolley.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此处要调用方法否则会变白板
        init();
    }

    //布局
    protected abstract int getLayout();

    //初始化控件
    protected abstract void initView();

    //监听
    protected abstract void setOnClick();

    //逻辑代码书写
    protected abstract void proLogic();

    void init() {
        if (getLayout() != 0) {
            setContentView(getLayout());
            initView();
            setOnClick();
            proLogic();
        }
    }

}
