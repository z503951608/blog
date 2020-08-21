package springcloud.club.blog.snowflake.constant;


public class ActionType {
    /** 操作类型---保存(type = 1)---保存单个实体 */
    public static final int SAVE = 1;
    /** 操作类型---update(type = 2)---更新单个实体 */
    public static final int UPDATE = 2;
    /** 操作类型---save_batch(type = 1)---批量保存实体列表 */
    public static final int SAVE_BATCH = 3;
    /** 操作类型---update_batch(type = 2)---批量更新实体列表 */
    public static final int UPDATE_BATCH = 4;
}
