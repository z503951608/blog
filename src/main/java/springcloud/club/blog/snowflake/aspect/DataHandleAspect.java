package springcloud.club.blog.snowflake.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import springcloud.club.blog.snowflake.bo.DataHandleBO;
import springcloud.club.blog.snowflake.conditional.DataHandleConditional;
import springcloud.club.blog.snowflake.constant.DataHandleConstant;

/**
 * @author zj
 * @create 2020-08-14 9:05
 * 切面，用于控制切那些方法
 **/
@Aspect
@Component
@Conditional(value = DataHandleConditional.class)
public class DataHandleAspect {
    @Pointcut(DataHandleConstant.DAO_EXECUTION)
    public void auditAspect() {

    }

    @Around(value = "auditAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        DataHandleBO dataHandleBO = new DataHandleBO();
        dataHandleBO.buildData(joinPoint);
        return dataHandleBO.procced();
    }
}
