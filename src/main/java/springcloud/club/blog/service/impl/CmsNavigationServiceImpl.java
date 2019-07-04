package springcloud.club.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.dao.CmsNavigationMapper;
import springcloud.club.blog.service.CmsNavigationService;
import springcloud.club.blog.utils.Query;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CmsNavigationServiceImpl implements CmsNavigationService{

    @Resource
    private CmsNavigationMapper cmsNavigationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cmsNavigationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CmsNavigation record) {
        return cmsNavigationMapper.insert(record);
    }

    @Override
    public int insertSelective(CmsNavigation record) {
        return cmsNavigationMapper.insertSelective(record);
    }

    @Override
    public CmsNavigation selectByPrimaryKey(Integer id) {
        return cmsNavigationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CmsNavigation record) {
        return cmsNavigationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CmsNavigation record) {
        return cmsNavigationMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CmsNavigation> list(Map<String,Object> params) {
        return cmsNavigationMapper.list(params);
    }

    @Override
    public int count(Map<String,Object> params) {
        return cmsNavigationMapper.count(params);
    }
}
