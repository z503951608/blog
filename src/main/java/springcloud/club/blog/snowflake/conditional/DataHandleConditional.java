package springcloud.club.blog.snowflake.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import springcloud.club.blog.snowflake.constant.DataHandleConstant;

/**
 * @author zj
 * @create 2020-08-14 9:15
 * 控制切面开关，是否开启
 **/
public class DataHandleConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取上下文环境
        Environment env = context.getEnvironment();
        Boolean auditSwitch = env.getProperty(DataHandleConstant.DATAHANDLE_SWITCH, Boolean.class);
        if (auditSwitch == null) {
            return true;
        }
        return auditSwitch;
    }
}
