package springcloud.club.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zj
 * @create 2019-06-06 14:24
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * @author zj
     * @description 后台主页
     * @version V1.0
     * @date 2019/6/6 14:27
     * @param
     * @return java.lang.String
     * @see AdminController#toIndex
     */
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/admin/index";
    }
}
