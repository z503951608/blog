package springcloud.club.blog.snowflake.factory;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 对象创建工厂
 *
 */
@Slf4j
public class BeanFactory {
	/**
	 * 根据beanClazz 创建对象
	 *
	 * @param beanClazz : Class : 泛型类的class
	 * @return
	 * @创建时间 2018年4月14日 上午11:54:45
	 */
	public static <T> T newInstance(Class<T> beanClazz) {
		try {
			return beanClazz.newInstance();
		} catch (InstantiationException e) {
			log.error("运行失败",e);
		} catch (IllegalAccessException e) {
			log.error("运行失败",e);
		}
		return null;
	}
}
