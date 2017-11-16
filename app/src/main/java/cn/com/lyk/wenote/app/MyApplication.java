package cn.com.lyk.wenote.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.xutils.x;

/**
 * Created by lyk on 2017/11/1.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//初始化xUtils
        Logger.addLogAdapter(new AndroidLogAdapter());//Initialize
        x.Ext.setDebug(true);

    }
}