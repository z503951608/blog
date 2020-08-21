package springcloud.club.blog.domain;

import java.io.Serializable;
import lombok.Data;
import springcloud.club.blog.snowflake.entity.BaseEntity;

@Data
public class CmsNavigation extends BaseEntity<CmsNavigation> implements Serializable {

    /**
    * 名称
    */
    private String name;

    /**
    * 是否显示
    */
    private String isShow;

    /**
    * 排序
    */
    private Integer isOrder;

    /**
    * 菜单路径
    */
    private String url;

    private static final long serialVersionUID = 1L;
}