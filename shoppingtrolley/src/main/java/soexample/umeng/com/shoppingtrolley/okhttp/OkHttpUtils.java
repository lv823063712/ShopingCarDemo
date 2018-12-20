package soexample.umeng.com.shoppingtrolley.okhttp;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {

    private final OkHttpClient okHttpClient;

    private OkHttpUtils() {
        this.okHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        return ViewHolder.okHttpUtils;
    }

    static class ViewHolder {
        private static final OkHttpUtils okHttpUtils = new OkHttpUtils();
    }

    //解析
    public void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);

    }

}
