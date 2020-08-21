package springcloud.club.blog.service;

import springcloud.club.blog.domain.CmsClassify;
import springcloud.club.blog.dao.CmsClassifyMapper;
public interface CmsClassifyService extends BaseService<CmsClassify>{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(CmsClassify record);

    CmsClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsClassify record);

    int updateByPrimaryKey(CmsClassify record);

}
