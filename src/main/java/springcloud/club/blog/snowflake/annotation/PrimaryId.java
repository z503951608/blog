package springcloud.club.blog.snowflake.annotation;



import springcloud.club.blog.snowflake.manage.BasePrimaryKeyGenerator;
import springcloud.club.blog.snowflake.manage.impl.PrimaryKeySnowflakeGenerator;

import java.lang.annotation.*;

/**
 * <p>
 * 主键生成器注解
 * </p>
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryId {
    Class<? extends BasePrimaryKeyGenerator> primaryKeyGeneratorClass() default PrimaryKeySnowflakeGenerator.class;
}
