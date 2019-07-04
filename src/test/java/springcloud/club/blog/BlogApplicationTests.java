package springcloud.club.blog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springcloud.club.blog.domain.CmsNavigation;
import springcloud.club.blog.service.CmsNavigationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private CmsNavigationService cmsNavigationService;

    @Test
    public void contextLoads() {
        System.out.print(cmsNavigationService.list(null));
    }

}
