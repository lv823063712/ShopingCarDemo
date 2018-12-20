package soexample.umeng.com.shoppingtrolley.ipresenter;

import soexample.umeng.com.shoppingtrolley.callback.MyCallBack;
import soexample.umeng.com.shoppingtrolley.iview.IView2;
import soexample.umeng.com.shoppingtrolley.moudle.MyMoudleImpl;


public class IPersenterImpl2 implements IPersenter {

    private IView2 iView2;
    private MyMoudleImpl myMoudle;

    public IPersenterImpl2(IView2 iView2) {
        this.iView2 = iView2;
        myMoudle = new MyMoudleImpl();
    }

    @Override
    public void startIPersenter(String url) {
        myMoudle.startRequest(url, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView2.success2(data);
            }

            @Override
            public void setError(Object error) {
                iView2.error2(error);
            }
        });
    }

    //防止内存泄漏
    public void onDatacth() {
        if (myMoudle != null) {
            myMoudle = null;
        }
        if (iView2 != null) {
            iView2 = null;
        }
    }

}
