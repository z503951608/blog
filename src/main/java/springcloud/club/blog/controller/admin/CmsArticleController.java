package springcloud.club.blog.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import springcloud.club.blog.domain.CmsArticle;
import springcloud.club.blog.domain.CmsArticleImage;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.service.CmsArticleImageService;
import springcloud.club.blog.service.CmsArticleService;
import springcloud.club.blog.service.CmsNavigationService;
import springcloud.club.blog.utils.HttpResponseDto;
import springcloud.club.blog.utils.Query;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zj
 * @create 2019-07-01 10:00
 * 文章详情
 **/
@Controller
@RequestMapping("/admin/cmsarticle/")
@Slf4j
public class CmsArticleController {

    @Autowired
    private CmsNavigationService cmsNavigationService;

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired CmsArticleImageService cmsArticleImageService;

    @Value("${springcloud.club.url}")
    private String URL;

    /**
     * @author zj
     * @description 跳转文章首页
     * @version V1.0
     * @date 2019/7/1 15:38
     * @param
     * @return java.lang.String
     * @see CmsArticleController#toIndex
     */
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/admin/cmsarticle/cmsarticle-list";
    }
    /**
     * @author zj
     * @description 文章列表
     * @version V1.0
     * @date 2019/7/1 15:38
     * @param params
     * @return springcloud.club.blog.utils.HttpResponseDto
     * @see CmsArticleController#list
     */
    @RequestMapping("/list")
    @ResponseBody
    public HttpResponseDto list(@RequestParam Map<String,Object> params){
        HttpResponseDto dto = new HttpResponseDto();
        try {
            if(params.containsKey("publicTimes") && !StringUtils.isEmpty(params.get("publicTimes").toString())){
                String startTime = params.get("publicTimes").toString().substring(0,19);
                params.put("startTime",startTime);
                String endTime = params.get("publicTimes").toString().substring(params.get("publicTimes").toString().length() - 19,params.get("publicTimes").toString().length());
                params.put("endTime",endTime);
            }
            if(params.containsKey("isShow") && StringUtils.isEmpty(params.get("isShow").toString())){
                params.put("isShow",null);
            }


            if(!params.containsKey("offset")){
                params.put("offset",0);
            }
            params.put("sort","public_time");
            params.put("order","asc");
            Query query = new Query(params);
            List<CmsArticle> cmsNavigations = cmsArticleService.list(query);
            int count = cmsArticleService.count(query);
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
            CmsArticle cmsArticle = cmsArticleService.selectByPrimaryKey(id);
            request.setAttribute("cmsArticle",cmsArticle);
        }
        List<CmsNavigation> cmsNavigations = cmsNavigationService.list(null);
        request.setAttribute("cmsNavigations",cmsNavigations);

        return "/admin/cmsarticle/cmsarticle-add";
    }

    /**
     * @author zj
     * @description 删除文章
     * @version V1.0
     * @date 2019/7/2 10:58
     * @param ids
     * @return springcloud.club.blog.utils.HttpResponseDto
     * @see CmsArticleController#remove
     */
    @RequestMapping("/remove")
    @ResponseBody
    public HttpResponseDto remove(@RequestParam("ids[]") Integer[] ids){
        try {
            if(null != ids && ids.length >0){
                for (Integer id : ids){
                    cmsArticleService.deleteByPrimaryKey(id);
                }
            }
            return HttpResponseDto.success();
        } catch (Exception e) {
            log.error("删除异常",e);
        }
        return HttpResponseDto.error();
    }
    /**
     * @author zj
     * @description 添加文章
     * @version V1.0
     * @date 2019/7/2 11:00
     * @param
     * @return springcloud.club.blog.utils.HttpResponseDto
     * @see CmsArticleController#add
     */
    @RequestMapping("/add")
    @ResponseBody
    public HttpResponseDto add(@RequestBody CmsArticle cmsArticle){
        try {
            if(StringUtils.isEmpty(cmsArticle.getId())){
                if(cmsArticleService.save(cmsArticle) > 0 ){
                    return HttpResponseDto.success("添加成功");
                }
            }else{
                if(cmsArticleService.updateByPrimaryKeySelective(cmsArticle) > 0){
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
     * @description 文件上传
     * @version V1.0
     * @date 2019/7/2 11:02
     * @param
     * @return
     * @see #
     */
    @RequestMapping("/upload")
    @ResponseBody
    public HttpResponseDto upload(@RequestParam("file") MultipartFile file,String type){
        HttpResponseDto dto = new HttpResponseDto();
        try {
            Map map = new HashMap();
            if (file.isEmpty()) {
                map.put("src","");
                dto.setCode(500);
                dto.setMsg("文件为空");
                dto.setData(map);
                return dto;
            }else{
                String fileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                File path = new File("/var/www/html/static/");
                if(!path.exists()){
                    path.mkdirs();
                }
                File dest = new File(path.getAbsolutePath()+ "/" + uuid.concat("-").concat(fileName));
                file.transferTo(dest); // 保存文件
                map.put("src",URL.concat("static/").concat(uuid).concat("-").concat(fileName));
                map.put("title",file.getOriginalFilename());
                dto.setCode(0);
                dto.setMsg("成功");
                dto.setData(map);
                return dto;
            }
        } catch (Exception e) {
            log.error("文件上传异常--->{}",e);
            return dto;
        }
    }
}
