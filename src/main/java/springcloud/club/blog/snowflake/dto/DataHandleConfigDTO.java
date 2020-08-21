package springcloud.club.blog.snowflake.dto;

/**
 * @author zj
 * @create 2020-08-14 11:00
 * 操作类型（方法是增删改查中的一种）
 **/
public class DataHandleConfigDTO {
    private String auditMethodName;
    private int actionType;
    private int type;
    public DataHandleConfigDTO(String auditMethodName, int actionType, int type) {
        this.auditMethodName = auditMethodName;
        this.actionType = actionType;
        this.type = type;
    }

    public String getAuditMethodName() {
        return auditMethodName;
    }

    public void setAuditMethodName(String auditMethodName) {
        this.auditMethodName = auditMethodName;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
