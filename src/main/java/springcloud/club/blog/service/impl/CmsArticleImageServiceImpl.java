package springcloud.club.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import springcloud.club.blog.dao.CmsArticleImageMapper;
import springcloud.club.blog.domain.CmsArticleImage;
import springcloud.club.blog.service.CmsArticleImageService;
@Service
public class CmsArticleImageServiceImpl extends BaseServiceImpl<CmsArticleImageMapper,CmsArticleImage> implements CmsArticleImageService{

    @Resource
    private CmsArticleImageMapper cmsArticleImageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cmsArticleImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CmsArticleImage record) {
        return cmsArticleImageMapper.insertSelective(record);
    }

    @Override
    public CmsArticleImage selectByPrimaryKey(Integer id) {
        return cmsArticleImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CmsArticleImage record) {
        return cmsArticleImageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CmsArticleImage record) {
        return cmsArticleImageMapper.updateByPrimaryKey(record);
    }

}
