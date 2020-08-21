package springcloud.club.blog.snowflake.constant;

/**
 * @author zj
 * @create 2020-08-14 9:09
 * 切面规则
 **/
public class DataHandleConstant {
    /**
     * 切点
     */
    public static final String DAO_EXECUTION ="execution(* *..dao..*Dao.*(..)) || execution(* *..mapper..*Mapper.*(..)) || execution(* *..repository..*Repository.*(..))";
    /**
     * 切面开关
     */
    public static final String DATAHANDLE_SWITCH = "data.handle.switch";
}
