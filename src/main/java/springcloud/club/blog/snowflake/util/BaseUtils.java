package springcloud.club.blog.snowflake.util;

/**
 * @author zj
 * @create 2020-08-14 10:30
 * 判断对象是否空
 **/
public class BaseUtils {
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }
}
