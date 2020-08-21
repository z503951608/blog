package springcloud.club.blog.snowflake.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SnowflakeIdWorkerProperties {
	/** 工作机器ID(0~31) */
	private static long workerId;

	@Value("${jdbc.snowflakeId.workerId:0}")
	public void setWorkerId(long workerId) {
		SnowflakeIdWorkerProperties.workerId = workerId;
	}

	public static long getWorkerId() {
		return workerId;
	}
}

