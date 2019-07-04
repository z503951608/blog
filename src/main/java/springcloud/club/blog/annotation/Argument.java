package springcloud.club.blog.annotation;

import java.lang.annotation.*;

/**
 * @author zj
 * @create 2019-06-05 11:57
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Argument {
}
