package soexample.umeng.com.shoppingtrolley.callback;

public interface MyCallBack<T> {

    void setData(T data);

    void setError(T error);
}
