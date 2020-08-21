package springcloud.club.blog.service;

import springcloud.club.blog.dao.CmsArticleImageMapper;
import springcloud.club.blog.domain.CmsArticleImage;
public interface CmsArticleImageService extends BaseService<CmsArticleImage>{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(CmsArticleImage record);

    CmsArticleImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticleImage record);

    int updateByPrimaryKey(CmsArticleImage record);

}
