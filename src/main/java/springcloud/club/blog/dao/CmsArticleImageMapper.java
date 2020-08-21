package springcloud.club.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import springcloud.club.blog.domain.CmsArticleImage;
@Mapper
public interface CmsArticleImageMapper extends BaseMapper<CmsArticleImage>{

    int deleteByPrimaryKey(Integer id);

    int insertSelective(CmsArticleImage record);

    CmsArticleImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticleImage record);

    int updateByPrimaryKey(CmsArticleImage record);
}