package springcloud.club.blog.snowflake.dto;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zj
 * @create 2020-08-14 11:02
 * 实体类的对象信息和值信息
 **/
public class DataHandleDTO {
    /** 实体对象的属性列表 */
    private List<Field> entityFields;
    /** 实体对象列表 */
    private List<?> entitys;
    /** 实体对象 */
    private Object entity;

    private Object auditor;

    public List<?> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<?> entitys) {
        this.entitys = entitys;
    }


    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public List<Field> getEntityFields() {
        return entityFields;
    }

    public void setEntityFields(List<Field> entityFields) {
        this.entityFields = entityFields;
    }

    public Object getAuditor() {
        return auditor;
    }

    public void setAuditor(Object auditor) {
        this.auditor = auditor;
    }

}
