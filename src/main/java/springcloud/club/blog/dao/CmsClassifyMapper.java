package springcloud.club.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import springcloud.club.blog.domain.CmsClassify;
@Mapper
public interface CmsClassifyMapper extends BaseMapper<CmsClassify>{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CmsClassify record);

    CmsClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsClassify record);

    int updateByPrimaryKey(CmsClassify record);
}