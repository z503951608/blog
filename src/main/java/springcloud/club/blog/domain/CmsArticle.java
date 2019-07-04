package springcloud.club.blog.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CmsArticle implements Serializable {
    /**
    * 
    */
    private Integer id;

    /**
    * 文章标题
    */
    private String title;

    /**
    * 发布时间
    */
    private Date publicTime;

    /**
    * 文章类容
    */
    private String content;

    /**
    * 标题ID对应cms_navigation表ID
    */
    private Integer navigationId;

    /**
    * 是否显示
    */
    private Integer isShow;

    /**
    * 标题图像
    */
    private String image;

    /**
    * 浏览量
    */
    private Integer views;

    private static final long serialVersionUID = 1L;
}