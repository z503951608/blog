package springcloud.club.blog.domain;

import java.io.Serializable;
import lombok.Data;
import springcloud.club.blog.snowflake.entity.BaseEntity;

@Data
public class CmsClassify extends BaseEntity<CmsClassify> implements Serializable {
    /**
    * 类别父ID
    */
    private Integer parentId;

    /**
    * 类别名称
    */
    private String className;

    /**
    * 排序
    */
    private Integer isOrder;

    private static final long serialVersionUID = 1L;
}