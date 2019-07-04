package springcloud.club.blog.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CmsComment implements Serializable {
    /**
    * 
    */
    private Integer id;

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