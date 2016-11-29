package demo.wen.com.mydemo.base;

import android.app.Application;
import android.content.Context;

import demo.wen.com.mydemo.BuildConfig;
import demo.wen.com.mydemo.utils.LogUtils;

/**
 * Created by wangenning on 2016/11/3.
 */

public class BaseApplication extends Application {
    private static BaseApplication baseApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //初始化logger
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
    }
    public static Context getAppContext() {
        return baseApplication;
    }

}
