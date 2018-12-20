package soexample.umeng.com.shoppingtrolley.ipresenter;

import soexample.umeng.com.shoppingtrolley.callback.MyCallBack;
import soexample.umeng.com.shoppingtrolley.iview.IView;
import soexample.umeng.com.shoppingtrolley.moudle.MyMoudleDImpl;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private MyMoudleDImpl myMoudleD;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        myMoudleD = new MyMoudleDImpl();
    }

    @Override
    public void startIPersenter(String url) {
        myMoudleD.startRequest(url, new MyCallBack() {
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

    //防止内存泄漏
    public void onDatacth() {
        if (myMoudleD != null) {
            myMoudleD = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
