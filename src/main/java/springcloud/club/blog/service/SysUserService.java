package springcloud.club.blog.service;

import springcloud.club.blog.dao.SysUserMapper;
import springcloud.club.blog.domain.SysUser;
import springcloud.club.blog.po.SysUserPO;

public interface SysUserService{


    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectUserLogin(SysUserPO sysUserPO);
}
