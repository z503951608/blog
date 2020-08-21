package springcloud.club.blog.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import springcloud.club.blog.snowflake.entity.BaseEntity;

@Data
public class SysUser extends BaseEntity<SysUser> implements Serializable {
    /**
    * 
    */
    private Long userId;

    /**
    * 用户名
    */
    private String username;

    /**
    * 
    */
    private String name;

    /**
    * 密码
    */
    private String password;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 状态 0:禁用，1:正常
    */
    private Byte status;

    /**
    * 创建用户id
    */
    private Long userIdCreate;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;

    /**
    * 性别
    */
    private Long sex;

    /**
    * 阳历生日出身日期
    */
    private Date birth;

    /**
    * 
    */
    private Long picId;

    /**
    * 现居住地
    */
    private String liveAddress;

    /**
    * 爱好
    */
    private String hobby;

    /**
    * 省份
    */
    private String province;

    /**
    * 所在城市
    */
    private String city;

    /**
    * 所在地区
    */
    private String district;

    /**
    * 
    */
    private Byte isdelete;

    /**
    * 账号
    */
    private String accountnum;

    /**
    * 微信号
    */
    private String weixinnum;

    /**
    * qq号
    */
    private String qqnum;

    /**
    * 身份证号
    */
    private String identity;

    /**
    * 农历生日
    */
    private Date lunarcalendar;

    /**
    * 备注
    */
    private String mark;

    /**
    * 姓名拼音
    */
    private String namepinyin;

    /**
    * 父账号id
    */
    private Integer pid;

    /**
    * 
    */
    private String avatar;

    private static final long serialVersionUID = 1L;
}