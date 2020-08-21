package springcloud.club.blog.snowflake.util;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 */
@Slf4j
public class ReflectionUtils {

    /**
     * <p>获取指定class类及其继承的所有父类除了Object类的属性</p>
     *
     * <pre></pre>
     *
     * @param targetClass
     * @return
     * @创建时间 2018年5月4日 下午10:14:33
     */
    public static List<Field> getDeclaredFieldsIncSup(final Class<?> targetClass) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> tempClass = targetClass;
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (BaseUtils.isNotNull(tempClass)) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        return fieldList;
    }

    /**
     * <p>
     * 创建class对应的实例对象
     * </p>
     *
     * @param clazz : Class : 泛型class
     * @return 泛型对象
     * <p>
     * 创建时间    2018年2月9日 上午10:18:55
     */
    public static <T> T newInstance(Class<T> clazz) {
        if (BaseUtils.isNull(clazz)) {
            return null;
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            log.error("运行失败", e);
        } catch (IllegalAccessException e) {
            log.error("运行失败", e);
        }
        return null;
    }
}
