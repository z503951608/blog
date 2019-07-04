package springcloud.club.blog.service;

import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.utils.Query;

import java.util.List;
import java.util.Map;

public interface CmsNavigationService{


    int deleteByPrimaryKey(Integer id);

    int insert(CmsNavigation record);

    int insertSelective(CmsNavigation record);

    CmsNavigation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsNavigation record);

    int updateByPrimaryKey(CmsNavigation record);

    List<CmsNavigation> list(Map<String,Object> params);

    int count(Map<String,Object> params);
}
