package util;

import java.util.regex.Pattern;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-01 上午8:58
 **/
public class StringUtil {

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumberic(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断一个字符串是否为字母
     *
     * @param fstrData
     * @return
     */
    public static boolean check(String fstrData) {
        char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }
}
