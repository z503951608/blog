package springcloud.club.blog.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class CmsArticleImage implements Serializable {
    /**
    * 
    */
    private Integer id;

    /**
    * 文章ID
    */
    private Integer articleId;

    /**
    * 文章中图片url
    */
    private String imageUrl;

    private static final long serialVersionUID = 1L;
}