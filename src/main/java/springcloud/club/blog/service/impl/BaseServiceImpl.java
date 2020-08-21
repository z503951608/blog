package springcloud.club.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import springcloud.club.blog.dao.BaseMapper;
import springcloud.club.blog.service.BaseService;
import springcloud.club.blog.snowflake.entity.BaseEntity;

import java.util.List;

/**
 * @author zj
 * @create 2020-08-21 15:58
 **/
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<D extends BaseMapper<T>, T extends BaseEntity<T>> implements BaseService<T> {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Override
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    @Override
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    @Override
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    @Override
    public Page<T> findPage(Page<T> page, T entity) {
        //entity.setPage(page);
        //page.setList(dao.findList(entity));
        dao.findList(entity);
        return page;
    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public int save(T entity) {
        if (entity.getId() == null){
            return dao.insert(entity);
        }else{
            return dao.update(entity);
        }
    }

    /**
     * 删除数据
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }
}
