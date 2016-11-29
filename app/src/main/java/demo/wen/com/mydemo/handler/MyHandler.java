package demo.wen.com.mydemo.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by wangenning on 2016/11/4.
 */

public class MyHandler extends Handler {

    private final WeakReference<Activity> mActivity;
    private OnHandlerListener onHandlerListener;

    public MyHandler(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
    }

    public void setOnHandlerListener(OnHandlerListener onHandlerListener) {
        this.onHandlerListener = onHandlerListener;
    }

    @Override
    public void handleMessage(Message msg) {
        if (onHandlerListener != null && mActivity != null) {
            onHandlerListener.onHandlerMessage(mActivity.get(), msg);
        }
        super.handleMessage(msg);
    }

}
