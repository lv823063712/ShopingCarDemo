package soexample.umeng.com.shoppingtrolley.ipresenter;

import soexample.umeng.com.shoppingtrolley.callback.MyCallBack;
import soexample.umeng.com.shoppingtrolley.iview.IView;
import soexample.umeng.com.shoppingtrolley.moudle.MyMoudleImpl3;

public class IPersenterImpl3 implements IPersenter {
    private IView iView;
    private MyMoudleImpl3 myMoudleImpl3;

    public IPersenterImpl3(IView iView) {
        this.iView = iView;
        myMoudleImpl3 = new MyMoudleImpl3();
    }

    @Override
    public void startIPersenter(String url) {
        myMoudleImpl3.startRequest(url, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.success(data);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    public void onDatacth(){
        if (myMoudleImpl3!=null){
            myMoudleImpl3 = null;
        }
        if (iView!=null){
            iView = null;
        }
    }

}
