package springcloud.club.blog.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class CmsClassify implements Serializable {
    /**
    * 
    */
    private Integer id;

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