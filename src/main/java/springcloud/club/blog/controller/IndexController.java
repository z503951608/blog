package springcloud.club.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springcloud.club.blog.domain.CmsArticle;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.service.CmsArticleService;
import springcloud.club.blog.service.CmsNavigationService;
import springcloud.club.blog.utils.HttpResponseDto;
import springcloud.club.blog.utils.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @create 2019-06-05 18:19
 **/
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {
    @Autowired
    private CmsNavigationService cmsNavigationService;
    @Autowired
    private CmsArticleService cmsArticleService;

    /**
     * @param request
     * @return java.lang.String
     * @author zj
     * @description 前端首页
     * @version V1.0
     * @date 2019/7/3 14:26
     * @see IndexController#index
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        List<CmsNavigation> cmsNavigations = cmsNavigationService.list(null);
        Map map = new HashMap();
        map.put("offset", 0);
        map.put("limit", 10);
        Query query = new Query(map);
        List<CmsArticle> cmsArticles = cmsArticleService.list(query);
        int count = cmsArticleService.count(query);

        request.setAttribute("cmsNavigations", cmsNavigations);
        request.setAttribute("cmsArticles", cmsArticles);
        request.setAttribute("count", count);
        return "index";
    }
    /**
     * @author zj
     * @description  文章分类查找
     * @version V1.0
     * @date 2019/7/3 15:09
     * @param
     * @return java.lang.String
     * @see IndexController#navigation
     */
    @RequestMapping("/navigation/{id}")
    public String navigation(HttpServletRequest request,@PathVariable String id){
        List<CmsNavigation> cmsNavigations = cmsNavigationService.list(null);
        Map map = new HashMap();
        map.put("offset", 0);
        map.put("limit", 10);
        map.put("navigationId",Integer.valueOf(id));
        Query query = new Query(map);
        List<CmsArticle> cmsArticles = cmsArticleService.list(query);
        int count = cmsArticleService.count(query);

        request.setAttribute("cmsNavigations", cmsNavigations);
        request.setAttribute("cmsArticles", cmsArticles);
        request.setAttribute("count", count);
        request.setAttribute("navigation",id);
        return "index";
    }
    /**
     * @param params
     * @return springcloud.club.blog.utils.HttpResponseDto
     * @author zj
     * @description 文章分页
     * @version V1.0
     * @date 2019/7/3 14:26
     * @see IndexController#list
     */
    @RequestMapping("/list")
    @ResponseBody
    public HttpResponseDto list(@RequestBody Map<String, Object> params) {
        HttpResponseDto ret = new HttpResponseDto();
        try {
            if (params.containsKey("curr")) {
                params.put("offset", (Integer.valueOf(params.get("curr").toString()
                ) - 1) * 10);
            }
            Query query = new Query(params);
            List<CmsArticle> cmsArticles = cmsArticleService.list(query);
            int count = cmsArticleService.count(query);
            ret.setCode(200);
            ret.setRows(cmsArticles);
            ret.setCount(count);
            return ret;
        } catch (Exception e) {
            log.error("分页异常->{}", params, e);
        }
        return HttpResponseDto.error("服务异常");
    }

    /**
     * @author zj
     * @description 文章详情
     * @version V1.0
     * @date 2019/7/3 15:34
     * @param
     * @return java.lang.String
     * @see IndexController#cmsarticle
     */
    @RequestMapping("/cmsarticle/{id}")
    public String cmsarticle(HttpServletRequest request,@PathVariable String id){
        List<CmsNavigation> cmsNavigations = cmsNavigationService.list(null);
        CmsArticle cmsArticle = cmsArticleService.selectByPrimaryKey(Integer.valueOf(id));
        Map map = new HashMap();
        map.put("offset", 0);
        map.put("limit", 10);
        map.put("navigationId",cmsArticle.getNavigationId());
        Query query = new Query(map);
        List<CmsArticle> cmsArticles = cmsArticleService.list(query);
        //上一篇下一篇

        Map piece = new HashMap();
        piece.put("navigationId",cmsArticle.getNavigationId());
        piece.put("id",Integer.valueOf(id));
        piece.put("flag","last");
        CmsArticle last = cmsArticleService.selectPiece(piece);
        piece.put("flag","next");
        CmsArticle next = cmsArticleService.selectPiece(piece);
        if(StringUtils.isEmpty(last)){
            last = cmsArticle;
        }
        if(StringUtils.isEmpty(next)){
            next = cmsArticle;
        }
        request.setAttribute("cmsNavigations", cmsNavigations);
        request.setAttribute("cmsArticle",cmsArticle);
        request.setAttribute("cmsArticles", cmsArticles);
        request.setAttribute("last", last);
        request.setAttribute("next", next);
        return "artile";
    }

    /**
     * @author zj
     * @description 文章检索
     * @version V1.0
     * @date 2019/7/3 16:37
     * @param
     * @return java.lang.String
     * @see IndexController#search
     */
    @RequestMapping("/search")
    public String search(HttpServletRequest request,@RequestParam Map<String,Object> params){
        List<CmsNavigation> cmsNavigations = cmsNavigationService.list(null);
        Map map = new HashMap();
        map.put("offset", 0);
        map.put("limit", 10);
        Query query = new Query(map);
        query.put("key",params.get("key").toString());
        List<CmsArticle> cmsArticles = cmsArticleService.list(query);
        int count = cmsArticleService.count(query);
        request.setAttribute("cmsNavigations", cmsNavigations);
        request.setAttribute("cmsArticles", cmsArticles);
        request.setAttribute("key", params.get("key").toString());
        request.setAttribute("count", count);
        return "index";
    }


}
