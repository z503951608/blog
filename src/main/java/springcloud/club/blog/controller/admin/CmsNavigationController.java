package springcloud.club.blog.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.service.CmsNavigationService;
import springcloud.club.blog.utils.HttpResponseDto;
import springcloud.club.blog.utils.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @create 2019-06-10 11:24
 * 导航
 **/
@Controller
@RequestMapping("/admin/cmsnavigation")
@Slf4j
public class CmsNavigationController {

    @Autowired
    private CmsNavigationService cmsNavigationService;
    /**
     * @author zj
     * @description导航管理首页
     * @version V1.0
     * @date 2019/6/10 16:01
     * @param
     * @return java.lang.String
     * @see CmsNavigationController#toIndex
     */
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/admin/cmsnavigation/cmsnavigation-list";
    }
    /**
     * @author zj
     * @description导航列表
     * @version V1.0
     * @date 2019/6/10 16:02
     * @param request
     * @param params
     * @return springcloud.club.blog.utils.HttpResponseDto
     * @see CmsNavigationController#index
     */
    @RequestMapping("list")
    @ResponseBody
    public HttpResponseDto index(HttpServletRequest request, @RequestParam Map<String,Object> params){
        HttpResponseDto dto = new HttpResponseDto();
        try {
            if(!params.containsKey("offset")){
                params.put("offset",0);
            }
            params.put("sort","is_order");
            params.put("order","asc");
            Query query = new Query(params);
            List<CmsNavigation> cmsNavigations = cmsNavigationService.list(query);
            int count = cmsNavigationService.count(query);
            dto.setData(cmsNavigations);
            dto.setCount(count);
            dto.setCode(0);
            return dto;
        } catch (Exception e) {
            log.error("获取数据异常",e);
        }
        return HttpResponseDto.error();
    }
    /**
     * @author zj
     * @description 跳转添加页面
     * @version V1.0
     * @date 2019/6/11 14:17
     * @param
     * @return java.lang.String
     * @see CmsNavigationController#toAdd
     */
    @RequestMapping("/toAdd")
    public String toAdd(@RequestParam(value = "id",required = false) Integer id,HttpServletRequest request){
        if(!StringUtils.isEmpty(id)){
            CmsNavigation cmsNavigation = cmsNavigationService.selectByPrimaryKey(id);
            request.setAttribute("cmsNavigation",cmsNavigation);
        }
        return "/admin/cmsnavigation/cmsnavigation-add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public HttpResponseDto add(@RequestBody CmsNavigation cmsNavigation){

        if(cmsNavigation.getIsShow() != null && cmsNavigation.getIsShow().equals("on")){
            cmsNavigation.setIsShow("1");
        }else{
            cmsNavigation.setIsShow("0");
        }
        try {
            if(cmsNavigation.getId() == null){
                if(cmsNavigationService.insert(cmsNavigation) >0){
                    return HttpResponseDto.success("添加成功");
                }
            }else{
                if(cmsNavigationService.updateByPrimaryKey(cmsNavigation) >0){
                    return HttpResponseDto.success("编辑成功");
                }
            }
        } catch (Exception e) {
            log.error("编辑异常",e);
        }
        return HttpResponseDto.error();
    }
    /**
     * @author zj
     * @description 删除导航
     * @version V1.0
     * @date 2019/6/11 16:42
     * @param ids
     * @return springcloud.club.blog.utils.HttpResponseDto
     * @see CmsNavigationController#remove
     */
    @RequestMapping("/remove")
    @ResponseBody
    public HttpResponseDto remove(@RequestParam("ids[]") Integer[] ids){
        try {
            if(null != ids && ids.length >0){
                for (Integer id : ids){
                    cmsNavigationService.deleteByPrimaryKey(id);
                }
            }
            return HttpResponseDto.success();
        } catch (Exception e) {
            log.error("删除异常",e);
        }
        return HttpResponseDto.error();
    }

}
