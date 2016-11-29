package demo.wen.com.mydemo.base;

import android.content.Context;

/**
 * Created by wangenning on 2016/11/3.
 */

public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
