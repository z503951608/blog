package springcloud.club.blog.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springcloud.club.blog.domain.SysUser;
import springcloud.club.blog.po.SysUserPO;
import springcloud.club.blog.service.SysUserService;
import springcloud.club.blog.utils.HttpResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zj
 * @create 2019-06-06 9:43
 **/
@Controller
@RequestMapping("/admin")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @author zj
     * @description 后台登录页面
     * @version V1.0
     * @date 2019/6/6 11:08
     * @param
     * @return java.lang.String
     * @see LoginController#toLogin
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/admin/login";
    }
    /**
     * @author zj
     * @description 用户登录
     * @version V1.0
     * @date 2019/6/6 11:08
     * @param
     * @return java.lang.String
     * @see LoginController#login
     */
    @PostMapping("/login")
    @ResponseBody
    public HttpResponseDto login(@RequestBody SysUserPO sysUserPO, HttpServletRequest request, HttpServletResponse response){
        try {
            sysUserPO.setPassword(DigestUtils.md5Hex(sysUserPO.getPassword() + sysUserPO.getUsername()));
            SysUser sysUser = sysUserService.selectUserLogin(sysUserPO);
            if(StringUtils.isEmpty(sysUser)){
               return HttpResponseDto.error("用户名或密码错误");
            }else{
                request.getSession().setAttribute("user",sysUser);
                return HttpResponseDto.success();
            }
        } catch (Exception e) {
            log.error("登录异常->{}",e);
        }
        return HttpResponseDto.error("服务器异常");
    }
    /**
     * @author zj
     * @description退出登录
     * @version V1.0
     * @date 2019/6/10 10:59
     * @param
     * @return java.lang.String
     * @see LoginController#loginOut
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("user");
        }
        return "/admin/login";
    }
}
