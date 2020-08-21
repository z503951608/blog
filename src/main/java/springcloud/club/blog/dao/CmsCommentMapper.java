package springcloud.club.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import springcloud.club.blog.domain.CmsComment;
@Mapper
public interface CmsCommentMapper extends BaseMapper<CmsComment>{
    int deleteByPrimaryKey(Integer id);
    
    int insertSelective(CmsComment record);

    CmsComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsComment record);

    int updateByPrimaryKey(CmsComment record);
}