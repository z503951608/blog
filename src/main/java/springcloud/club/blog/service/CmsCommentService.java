package springcloud.club.blog.service;

import springcloud.club.blog.domain.CmsComment;
import springcloud.club.blog.dao.CmsCommentMapper;
public interface CmsCommentService{


    int deleteByPrimaryKey(Integer id);

    int insert(CmsComment record);

    int insertSelective(CmsComment record);

    CmsComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsComment record);

    int updateByPrimaryKey(CmsComment record);

}
