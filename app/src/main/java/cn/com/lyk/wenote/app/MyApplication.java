package cn.com.lyk.wenote.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.xutils.x;

import java.io.File;

import cn.com.lyk.greendao.DaoMaster;
import cn.com.lyk.greendao.DaoSession;
import cn.com.lyk.wenote.utils.Const;

import static cn.com.lyk.wenote.utils.Const.APP_BASE_DIR;
import static cn.com.lyk.wenote.utils.Const.APP_BASE_DIR_DB;
import static cn.com.lyk.wenote.utils.Const.DB_NAME;

/**
 * Created by lyk on 2017/11/1.
 */

public class MyApplication extends Application {
    private DaoSession daoSession;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//初始化xUtils
        Logger.addLogAdapter(new AndroidLogAdapter());//Initialize
        x.Ext.setDebug(true);
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        mkdirs();
        initGreenDao();
        Logger.i("Application>>onCrate");
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, APP_BASE_DIR_DB + DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    private void mkdirs() {
        File file = new File(APP_BASE_DIR_DB);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
