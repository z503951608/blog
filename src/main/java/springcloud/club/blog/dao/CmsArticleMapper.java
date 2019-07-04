package springcloud.club.blog.dao;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import springcloud.club.blog.domain.CmsArticle;
import springcloud.club.blog.domain.CmsNavigation;

import java.util.List;
import java.util.Map;

@Mapper
public interface CmsArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsArticle record);

    int insertSelective(CmsArticle record);

    CmsArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticle record);

    int updateByPrimaryKey(CmsArticle record);

    List<CmsArticle> list(Map<String,Object> params);

    int count(Map<String,Object> params);

    CmsArticle selectPiece(Map<String,Object> params);
}