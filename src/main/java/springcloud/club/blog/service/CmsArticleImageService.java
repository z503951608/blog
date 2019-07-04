package springcloud.club.blog.service;

import springcloud.club.blog.dao.CmsArticleImageMapper;
import springcloud.club.blog.domain.CmsArticleImage;
public interface CmsArticleImageService{


    int deleteByPrimaryKey(Integer id);

    int insert(CmsArticleImage record);

    int insertSelective(CmsArticleImage record);

    CmsArticleImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticleImage record);

    int updateByPrimaryKey(CmsArticleImage record);

}