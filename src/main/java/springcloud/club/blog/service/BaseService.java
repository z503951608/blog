package springcloud.club.blog.service;


import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T> {
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity);

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    public int save(T entity);

    /**
     * 删除数据
     * @param entity
     */
    public void delete(T entity);
}
