package springcloud.club.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import springcloud.club.blog.domain.SysUser;
import springcloud.club.blog.po.SysUserPO;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{
    int deleteByPrimaryKey(Long userId);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectUserLogin(SysUserPO sysUserPO);
}