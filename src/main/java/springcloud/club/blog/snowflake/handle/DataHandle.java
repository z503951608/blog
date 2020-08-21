package springcloud.club.blog.snowflake.handle;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.annotation.*;
import org.springframework.util.Assert;
import springcloud.club.blog.snowflake.annotation.PrimaryId;
import springcloud.club.blog.snowflake.bo.DataHandleBO;
import springcloud.club.blog.snowflake.constant.ActionType;
import springcloud.club.blog.snowflake.constant.DataHandleType;
import springcloud.club.blog.snowflake.dto.DataHandleConfigDTO;
import springcloud.club.blog.snowflake.dto.DataHandleDTO;
import springcloud.club.blog.snowflake.factory.BeanFactory;
import springcloud.club.blog.snowflake.manage.BasePrimaryKeyGenerator;
import springcloud.club.blog.snowflake.util.BaseUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 数据层切面处理
 */
@Slf4j
public class DataHandle {
    protected DataHandleBO dataHandleBO;
    protected DataHandleConfigDTO dataHandleConfigDTO;
    protected DataHandleDTO dataHandleDTO;
    protected ProceedingJoinPoint joinPoint;

    public DataHandleConfigDTO getDataHandleConfigDTO() {
        return dataHandleConfigDTO;
    }

    public DataHandleDTO getDataHandleDTO() {
        return dataHandleDTO;
    }

    /**
     * 处理方法
     */
    public final Object procced(DataHandleBO dataHandleBO) throws Throwable {
        initData(dataHandleBO);
        return this.doProcced();
    }

    private final Object doProcced() throws Throwable {
        Object[] newArgs = buildNewArgs();
        if (ArrayUtils.isNotEmpty(newArgs)) {
            return joinPoint.proceed(newArgs);
        }
        return joinPoint.proceed();
    }

    private final void initData(DataHandleBO dataHandleBO) {
        this.dataHandleBO = dataHandleBO;
        this.dataHandleDTO = dataHandleBO.getDataHandleDTO();
        this.joinPoint = dataHandleBO.getJoinPoint();
        this.dataHandleConfigDTO = dataHandleBO.getDataHandleConfigDTO();
    }


    protected final Object[] buildNewArgs() throws Exception {
        int actionType = dataHandleConfigDTO.getActionType();
        Assert.notNull(actionType, "actionTypeEnum cant null");
        switch (actionType) {
            case ActionType.SAVE:
                return doSave();
            case ActionType.UPDATE:
                return doUpdate();
            case ActionType.SAVE_BATCH:
                return doSaveBatch();
            case ActionType.UPDATE_BATCH:
                return doUpdateBatch();
            default:
                return doOrtherAction(actionType);
        }
    }

    /**
     * 执行其他的操作类型---子类可以通过重写此方法来实现定制化需求
     */
    protected Object[] doOrtherAction(int actionType) {
        return joinPoint.getArgs();
    }

    /**
     * 执行保存
     *
     * @throws Exception
     */
    protected Object[] doSave() throws Exception {
        return doSingleCore();
    }

    /**
     * 执行更新
     *
     * @throws Exception
     */
    protected Object[] doUpdate() throws Exception {
        return doSingleCore();
    }

    /**
     * 执行单个实体核心方法
     */
    protected Object[] doSingleCore() throws Exception {
        Object[] args = joinPoint.getArgs();
        args[0] = buildNewEntity(dataHandleDTO.getEntity());
        return args;
    }

    /**
     * 执行批量保存
     */
    protected Object[] doSaveBatch() throws Exception {
        return doBatchCore();
    }

    /**
     * 执行批量更新
     */
    protected Object[] doUpdateBatch() throws Exception {
        return doBatchCore();
    }

    /**
     * 执行批量核心方法
     */
    protected Object[] doBatchCore() throws Exception {
        for (Object entity : dataHandleDTO.getEntitys()) {
            buildNewEntity(entity);
        }
        Object[] args = joinPoint.getArgs();
        args[0] = dataHandleDTO.getEntitys();
        return args;
    }

    /**
     * <p>
     * 构建新的持久化对象
     * </p>
     */
    protected final Object buildNewEntity(final Object entity) throws Exception {
        for (Field field : dataHandleDTO.getEntityFields()) {
            field.setAccessible(true);
            boolean isSaveType = dataHandleConfigDTO.getType() == DataHandleType.SAVE;
            if (isPrimaryKey(field)) {
                // 构建主键生成器
                buildPrimarykey(entity, field, isSaveType);
            } else {
                // 构建审计数据
                buildAuditData(entity, field, isSaveType);
            }
        }
        log.debug("entity===={}", JSON.toJSONString(entity));
        return entity;
    }

    private void buildPrimarykey(Object entity, Field field, boolean isSaveType) throws Exception {
        // 主键生成器
        if (isSaveType && isPrimaryKey(field)) {
            Serializable primaryKey = getPrimarykey(entity, field, isSaveType);
            if (BaseUtils.isNotNull(primaryKey)) {
                field.set(entity, primaryKey);
            }
        }
    }


    /**
     * 判断当前属性是否是主键
     */
    private boolean isPrimaryKey(Field field) {
        if (field.isAnnotationPresent(PrimaryId.class)) {
            return true;
        }
        return false;
    }

    /**
     * 构建修改数据数据
     */
    private void buildAuditData(Object entity, Field field, boolean isSaveType) throws Exception {
        Date currentDate = new Date();
        if (isSaveType && field.isAnnotationPresent(CreatedBy.class)) {
            field.set(entity, dataHandleDTO.getAuditor());
        } else if (isSaveType && field.isAnnotationPresent(CreatedDate.class)) {
            field.set(entity, currentDate);
        } else if (field.isAnnotationPresent(LastModifiedDate.class)) {
            field.set(entity, currentDate);
        } else if (field.isAnnotationPresent(LastModifiedBy.class)) {
            field.set(entity, dataHandleDTO.getAuditor());
        } else if (field.isAnnotationPresent(Version.class)) {
            field.set(entity, getVersion(entity, field, isSaveType));
        }
    }


    /**
     * <p>
     * 获取主键
     * </p>
     *
     * @param entity
     * @param field
     * @param isSaveType
     * @return
     * @throws Exception
     * @创建时间 2018年5月8日 下午8:51:18
     */
    private final Serializable getPrimarykey(Object entity, Field field, boolean isSaveType) throws Exception {
        // 主键生成器
        PrimaryId generatorAnnotation = field.getAnnotation(PrimaryId.class);
        if (generatorAnnotation == null) {
            return null;
        }
        Class<? extends BasePrimaryKeyGenerator> clazz = generatorAnnotation.primaryKeyGeneratorClass();
        Assert.notNull(clazz, "primaryKeyGeneratorClass cant null");
        BasePrimaryKeyGenerator basePrimaryKeyGenerator = BeanFactory.newInstance(clazz);
        Assert.notNull(clazz, "primaryKeyGenerator obj create fail");
        return basePrimaryKeyGenerator.generate(entity);
    }


    protected Object getVersion(Object enetity, Field field, boolean isSaveType) {
        return 0;
    }


}
