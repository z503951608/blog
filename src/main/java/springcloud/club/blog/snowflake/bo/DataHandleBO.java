package springcloud.club.blog.snowflake.bo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.Assert;
import springcloud.club.blog.snowflake.annotation.DBDataHandle;
import springcloud.club.blog.snowflake.dto.DataHandleConfigDTO;
import springcloud.club.blog.snowflake.dto.DataHandleDTO;
import springcloud.club.blog.snowflake.handle.DataHandle;
import springcloud.club.blog.snowflake.util.BaseUtils;
import springcloud.club.blog.snowflake.util.CollectionsUtils;
import springcloud.club.blog.snowflake.util.ReflectionUtils;

import java.util.List;

/**
 * @author zj
 * @create 2020-08-14 10:06
 * 组合类包含了
 * 操作类型（方法是增删改查中的一种）DataHandleConfigDTO
 * 实体类的对象信息和值信息 DataHandleDTO
 * 切面业务逻辑转换对象类 BaseAspectBO
 **/
@Slf4j
public class DataHandleBO extends BaseAspectBO {
    protected DataHandleConfigDTO dataHandleConfigDTO;
    protected DataHandleDTO dataHandleDTO;

    public DataHandleBO() {
        this(new DataHandleDTO());
    }

    public DataHandleBO(DataHandleDTO jdbcAuditorDTO) {
        this.dataHandleDTO = jdbcAuditorDTO;
    }
    public final DataHandleBO buildData(ProceedingJoinPoint joinPoint) {
        // 构建基础切面数据
        super.buildBaseAspectData(joinPoint);
        // 构建配置数据传输对象
        buildAuditConfigDTO();
        if (dataHandleConfigDTO != null) {
            // 构建实体数据
            buildEntityData();
        }
        return this;
    }
    private void buildEntityData() {
        if (ArrayUtils.isEmpty(super.baseAspectDTO.getTargetParameterValues())) {
            return;
        }
        //取save(do1,do2);或者update(List<do1>,do2);的第一个参数
        Object arg = super.getTParam(1);
        Assert.notNull(arg, "save or update obj cant null");
        Class<?> entityClass = null;
        if (arg instanceof List) {
            List<?> entitys = (List<?>) arg;
            if (CollectionsUtils.isEmpty(entitys)) {
                throw new RuntimeException("batch save data cant null");
            }
            dataHandleDTO.setEntitys(entitys);
            entityClass = entitys.get(0).getClass();
        } else {
            dataHandleDTO.setEntity(arg);
            entityClass = arg.getClass();
        }
        this.dataHandleDTO.setEntityFields(ReflectionUtils.getDeclaredFieldsIncSup(entityClass));
    }

    /** 处理方法 */
    public final Object procced() {
        if (BaseUtils.isNull(dataHandleConfigDTO)) {
            return super.proceed();
        }
        try {
            return new DataHandle().procced(this);
        } catch (Throwable e) {
            log.error("运行失败",e);
            return super.proceed();
        }
    }
    private final void buildAuditConfigDTO() {
        String auditMethodName = super.baseAspectDTO.getTargetMethodName();
        // 从配置容器中获取配置数据传输对象

        // 获取注解
        DBDataHandle auditAnnotation = super.targetMethod.getAnnotation(DBDataHandle.class);
        if (BaseUtils.isNull(auditAnnotation) && BaseUtils.isNull(dataHandleConfigDTO)) {
            return ;
        }
        if (BaseUtils.isNotNull(dataHandleConfigDTO)) {
            return ;
        }
        dataHandleConfigDTO = new DataHandleConfigDTO(auditMethodName, auditAnnotation.actionType(), auditAnnotation.type());
    }

    public DataHandleConfigDTO getDataHandleConfigDTO() {
        return dataHandleConfigDTO;
    }

    public DataHandleDTO getDataHandleDTO() {
        return dataHandleDTO;
    }
}
