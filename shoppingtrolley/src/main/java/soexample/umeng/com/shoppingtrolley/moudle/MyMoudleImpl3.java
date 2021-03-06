package soexample.umeng.com.shoppingtrolley.moudle;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.shoppingtrolley.bean.MySetData;
import soexample.umeng.com.shoppingtrolley.callback.MyCallBack;
import soexample.umeng.com.shoppingtrolley.okhttp.OkHttpUtils;

public class MyMoudleImpl3 implements MyMoudle {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String jsons = (String) msg.obj;
            Gson gson = new Gson();
            MySetData mySetData = gson.fromJson(jsons, MySetData.class);
            myCallBack.setData(mySetData);
        }
    };

    private MyCallBack myCallBack;

    @Override
    public void startRequest(String url, MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        OkHttpUtils.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.sendMessage(handler.obtainMessage(0, response.body().string()));
            }
        });
    }
}

