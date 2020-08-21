package springcloud.club.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import springcloud.club.blog.domain.CmsComment;
import springcloud.club.blog.dao.CmsCommentMapper;
import springcloud.club.blog.service.CmsCommentService;
@Service
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper,CmsComment> implements CmsCommentService{

    @Resource
    private CmsCommentMapper cmsCommentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cmsCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CmsComment record) {
        return cmsCommentMapper.insertSelective(record);
    }

    @Override
    public CmsComment selectByPrimaryKey(Integer id) {
        return cmsCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CmsComment record) {
        return cmsCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CmsComment record) {
        return cmsCommentMapper.updateByPrimaryKey(record);
    }

}
