package springcloud.club.blog.service;

import springcloud.club.blog.dao.SysUserMapper;
import springcloud.club.blog.domain.SysUser;
import springcloud.club.blog.po.SysUserPO;

public interface SysUserService extends BaseService<SysUser>{


    int deleteByPrimaryKey(Long userId);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectUserLogin(SysUserPO sysUserPO);
}
