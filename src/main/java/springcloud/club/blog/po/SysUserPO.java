package springcloud.club.blog.po;

import lombok.Data;

/**
 * @author zj
 * @create 2019-06-06 11:42
 **/
@Data
public class SysUserPO {
    //用户名
    private String username;
    //密码
    private String password;
    //验证码
    private String verity;
}
