package cn.com.lyk.wenote.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lyk on 2017/11/7.
 */

public class DateUtil {
    public static Calendar calendar = Calendar.getInstance();
    /**
     *
     * @return
     *  yyyy-MM-dd HH:mm:ss
     *  2012-12-29 23:47:36
     */
    public static String getFullDate() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(calendar.getTime());
    }

}
