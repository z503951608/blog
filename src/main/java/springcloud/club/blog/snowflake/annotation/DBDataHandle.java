package springcloud.club.blog.snowflake.annotation;

import java.lang.annotation.*;
/** 
 * @author zj 
 * @description 注解在 *Dao的方法上面
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBDataHandle {
    /** 操作类型常量---参考EcAuditConstant.EcActionType类 */
    int actionType();

    /** 类型 1：保存，2：更新---参考EcAuditConstant.EcType */
    int type();
}
