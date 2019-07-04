package springcloud.club.blog.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class CmsNavigation implements Serializable {
    /**
    * 主键ID
    */
    private Integer id;

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