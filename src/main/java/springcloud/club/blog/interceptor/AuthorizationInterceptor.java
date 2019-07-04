package springcloud.club.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import springcloud.club.blog.annotation.Authorization;
import springcloud.club.blog.domain.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zj
 */
@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("--------------------↓↓↓↓↓↓↓  preHandle  ↓↓↓↓↓↓↓------------------------");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
/*        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authorization methodAnnotation = method.getAnnotation(Authorization.class);*/
        SysUser sysUser =  (SysUser) request.getSession().getAttribute("user");
        if(StringUtils.isEmpty(sysUser)){
            response.sendRedirect("/admin/toLogin");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("-------------------------------↓↓↓↓↓  postHandle  ↓↓↓↓↓------------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("-------------------------------↑↑↑↑↑  afterCompletion ↑↑↑↑↑------------------------------");
    }
}
