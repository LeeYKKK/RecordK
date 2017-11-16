package cn.com.lyk.wenote.utils;

import java.util.regex.Pattern;

/**
 * Created by lyk on 2017/11/14.
 */

public class RegexUtil {
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
}
