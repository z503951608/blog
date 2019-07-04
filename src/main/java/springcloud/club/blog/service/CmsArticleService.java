package springcloud.club.blog.service;

import springcloud.club.blog.domain.CmsArticle;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.utils.Query;

import java.util.List;
import java.util.Map;

public interface CmsArticleService{


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
