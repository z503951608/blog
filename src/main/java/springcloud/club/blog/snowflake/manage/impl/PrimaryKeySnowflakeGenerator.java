package springcloud.club.blog.snowflake.manage.impl;

import springcloud.club.blog.snowflake.config.SnowflakeIdWorkerProperties;
import springcloud.club.blog.snowflake.entity.BaseEntity;
import springcloud.club.blog.snowflake.manage.BasePrimaryKeyGenerator;

import java.io.Serializable;

/**
 * 
 * <p>
 * 雪花主键生成器
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *

 * @创建时间 2018年5月8日 下午8:04:56
 */

public class PrimaryKeySnowflakeGenerator implements BasePrimaryKeyGenerator {

	@Override
	public Serializable generate(Object entityObj) {
		if (entityObj != null && entityObj instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) entityObj;
			if (baseEntity.getId() != null) {
				return baseEntity.getId();
			}
		}
		Long workerId = SnowflakeIdWorkerProperties.getWorkerId();
		return SnowflakeIdAlgorithm.getSingleInstance(workerId).nextId();
	}

}
