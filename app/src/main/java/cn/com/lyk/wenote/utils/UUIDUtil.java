package cn.com.lyk.wenote.utils;

import java.util.UUID;

/**
 * Created by lyk on 2017/12/5.
 */

public class UUIDUtil {
    public static String genUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
