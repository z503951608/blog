package springcloud.club.blog.domain;

import java.io.Serializable;
import lombok.Data;
import springcloud.club.blog.snowflake.entity.BaseEntity;

@Data
public class CmsArticleImage extends BaseEntity<CmsArticleImage> implements Serializable {
    /**
    * 文章ID
    */
    private Integer articleId;

    /**
    * 文章中图片url
    */
    private String imageUrl;
}