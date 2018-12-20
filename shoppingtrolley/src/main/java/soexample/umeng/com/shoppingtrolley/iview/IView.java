package soexample.umeng.com.shoppingtrolley.iview;

public interface IView<T> {

    void success(T data);

    void error(T error);

}
