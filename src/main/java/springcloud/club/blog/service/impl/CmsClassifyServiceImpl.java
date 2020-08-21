package springcloud.club.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import springcloud.club.blog.domain.CmsClassify;
import springcloud.club.blog.dao.CmsClassifyMapper;
import springcloud.club.blog.service.CmsClassifyService;
@Service
public class CmsClassifyServiceImpl extends BaseServiceImpl<CmsClassifyMapper,CmsClassify> implements CmsClassifyService{

    @Resource
    private CmsClassifyMapper cmsClassifyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cmsClassifyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CmsClassify record) {
        return cmsClassifyMapper.insertSelective(record);
    }

    @Override
    public CmsClassify selectByPrimaryKey(Integer id) {
        return cmsClassifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CmsClassify record) {
        return cmsClassifyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CmsClassify record) {
        return cmsClassifyMapper.updateByPrimaryKey(record);
    }

}
