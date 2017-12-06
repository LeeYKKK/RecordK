package cn.com.lyk.wenote.utils;

import android.os.Environment;

/**
 * Created by lyk on 2017/12/1.
 */

public class Const {
    public static final String  APP_BASE_DIR = Environment.getExternalStorageDirectory()+"/weNote/";
    public static final String APP_BASE_DIR_DB=Environment.getExternalStorageDirectory()+"/weNote/interior/db/";
    public static  final String DB_NAME="wenote.db";
    public static  final  int REQUEST_TAKE_PHOTO_CODE=1;//相机拍照成功code
}
