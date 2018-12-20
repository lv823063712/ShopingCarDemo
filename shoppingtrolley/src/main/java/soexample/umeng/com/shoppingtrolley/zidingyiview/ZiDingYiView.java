package soexample.umeng.com.shoppingtrolley.zidingyiview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import soexample.umeng.com.shoppingtrolley.R;

public class ZiDingYiView extends LinearLayout implements View.OnClickListener {


    private TextView delete_tv;
    private TextView product_number_tv;
    private TextView add_tv;
    private int myCount;

    public ZiDingYiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.add_remove_view, this);
        initView();
    }

    private void initView() {
        delete_tv = findViewById(R.id.delete_tv);
        product_number_tv = findViewById(R.id.product_number_tv);
        add_tv = findViewById(R.id.add_tv);

        delete_tv.setOnClickListener(this);
        add_tv.setOnClickListener(this);
    }

    //初始化赋值
    public void setNumber(int number) {
        this.myCount = number;
        product_number_tv.setText(number + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_tv:
                if (myCount > 0) {
                    myCount--;
                    product_number_tv.setText(myCount + "");
                    if (myOnCountChange != null) {
                        myOnCountChange.setCount(myCount);
                    }
                } else {
                    Toast.makeText(getContext(), "商品已售空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_tv:
                myCount++;
                product_number_tv.setText(myCount + "");
                if (myOnCountChange != null) {
                    myOnCountChange.setCount(myCount);
                }
                break;
        }
    }

    public interface onCountChange {
        void setCount(int count);
    }

    private onCountChange myOnCountChange;

    public void setOnChange(onCountChange myOnCountChange) {
        this.myOnCountChange = myOnCountChange;

    }
}
