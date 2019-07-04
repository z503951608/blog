package springcloud.club.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import springcloud.club.blog.domain.CmsArticle;
import springcloud.club.blog.dao.CmsArticleMapper;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.service.CmsArticleService;
import springcloud.club.blog.utils.Query;

import java.util.List;
import java.util.Map;

@Service
public class CmsArticleServiceImpl implements CmsArticleService{

    @Resource
    private CmsArticleMapper cmsArticleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cmsArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CmsArticle record) {
        return cmsArticleMapper.insert(record);
    }

    @Override
    public int insertSelective(CmsArticle record) {
        return cmsArticleMapper.insertSelective(record);
    }

    @Override
    public CmsArticle selectByPrimaryKey(Integer id) {
        return cmsArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CmsArticle record) {
        return cmsArticleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CmsArticle record) {
        return cmsArticleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CmsArticle> list(Map<String, Object> params) {
        return cmsArticleMapper.list(params);
    }

    @Override
    public int count(Map<String,Object> params) {
        return cmsArticleMapper.count(params);
    }

    @Override
    public CmsArticle selectPiece(Map<String, Object> params) {
        return cmsArticleMapper.selectPiece(params);
    }
}
