package springcloud.club.blog.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import springcloud.club.blog.snowflake.entity.BaseEntity;

@Data
public class CmsComment extends BaseEntity<CmsComment> implements Serializable {

    /**
    * 文章ID
    */
    private Integer articleId;

    /**
    * 评价类容
    */
    private String comment;

    /**
    * 评价时间
    */
    private Date addTime;

    /**
    * 是否显示
    */
    private Integer isShow;

    private static final long serialVersionUID = 1L;
}