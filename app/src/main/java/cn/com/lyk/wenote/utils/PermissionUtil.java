package cn.com.lyk.wenote.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by lyk on 2017/11/19.
 */

public class PermissionUtil  {
    public static final int REQUEST_WRITE=1;//申请权限的请求码

    public static void checkVersion(Activity context) {
        if(Build.VERSION.SDK_INT>=23){
            //判断是否有这个权限
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                //2、申请权限: 参数二：权限的数组；参数三：请求码
                ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE);
            }
        }
    }
}
