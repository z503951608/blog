package springcloud.club.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import springcloud.club.blog.domain.CmsNavigation;

import java.util.List;
import java.util.Map;

@Mapper
public interface CmsNavigationMapper extends BaseMapper<CmsNavigation>{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CmsNavigation record);

    CmsNavigation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsNavigation record);

    int updateByPrimaryKey(CmsNavigation record);

    List<CmsNavigation> list(Map<String,Object> params);

    int count(Map<String,Object> params);
}