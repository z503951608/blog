package springcloud.club.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Slf4j
public class BlogApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BlogApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        log.info("bean总数:{}", ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            log.info("{},beanName:{}", ++i, str);
        }
    }

}
