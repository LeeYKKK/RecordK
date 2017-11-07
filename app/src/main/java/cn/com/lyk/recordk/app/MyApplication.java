package cn.com.lyk.recordk.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by lyk on 2017/11/1.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//初始化xUtils

    }
}
